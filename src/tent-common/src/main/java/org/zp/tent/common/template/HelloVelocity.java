package org.zp.tent.common.template;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

public class HelloVelocity {
	public static void main(String[] args) {
		// 获取模板
		VelocityEngine ve = new VelocityEngine();
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		ve.init();
		Template t = ve.getTemplate("templates/hellovelocity.vm");

		// 填入velocity的内容
		VelocityContext ctx = new VelocityContext();
		ctx.put("name", "velocity");
		ctx.put("date", (new Date()).toString());
		List<String> colors = new ArrayList<String>();
		colors.add("red");
		colors.add("yellow");
		colors.add("blue");
		ctx.put("list", colors);

		StringWriter sw = new StringWriter();
		t.merge(ctx, sw);

		System.out.println(sw.toString());
	}
}