package com.zp.tent.db.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.zp.tent.db.model.Country;
import com.zp.tent.db.service.CountryService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

@Service("countryService")
public class CountryServiceImpl extends BaseService<Country> implements CountryService {

	@Override
	public List<Country> selectByCountry(Country country, int page, int rows) {
		Example example = new Example(Country.class);
		Example.Criteria criteria = example.createCriteria();
		if (StringUtil.isNotEmpty(country.getCountryname())) {
			criteria.andLike("countryname", "%" + country.getCountryname() + "%");
		}
		if (StringUtil.isNotEmpty(country.getCountrycode())) {
			criteria.andLike("countrycode", "%" + country.getCountrycode() + "%");
		}
		if (country.getId() != null) {
			criteria.andEqualTo("id", country.getId());
		}
		// 分页查询
		PageHelper.startPage(page, rows);
		return selectByExample(example);
	}

}
