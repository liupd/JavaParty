package org.zp.tent.common.util;

import java.util.ArrayList;
import java.util.List;

public class MultiResponseUtil {
	private int count;
	private boolean success;
	private List<ResponseUtil> rows = null;

	public MultiResponseUtil() {
		this.count = 0;
		this.success = true;
		rows = new ArrayList<ResponseUtil>();
	}

	public MultiResponseUtil(boolean success, int count,
			List<ResponseUtil> rows) {
		this.success = true;
		this.count = count;
		this.rows = rows;
	}

	public boolean add(ResponseUtil resp) {
		boolean flag = this.rows.add(resp);
		if (flag) {
			this.count++;
		}
		return flag;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<ResponseUtil> getRows() {
		return rows;
	}

	public void setRows(List<ResponseUtil> rows) {
		this.rows = rows;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}