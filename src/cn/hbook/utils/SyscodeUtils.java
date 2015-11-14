package cn.hbook.utils;

import java.util.List;
import java.util.Map;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


public class SyscodeUtils extends HibernateDaoSupport {

	@SuppressWarnings("unchecked")
	public List<Map<String, String>> getOption(String fname) {
		List<Map<String, String>> list = this
				.getHibernateTemplate()
				.find(
						"select new map(fvalue as fvalue,fkey as fkey) from TSyscode where fname=?",
						fname);
		return list;

	}

	@SuppressWarnings("unchecked")
	public List<Map<String, String>> getOption(String fname, String parentFKey) {
		Object args[] = { fname, parentFKey };
		List<Map<String, String>> list = this
				.getHibernateTemplate()
				.find(
						"select new map(fvalue as fvalue,fkey as fkey) from TSyscode where fname=? and parentKey=?",
						args);
		return list;

	}
	@SuppressWarnings("unchecked")
	public String getSfname(String fkey){
		System.out.println(fkey);
		String hql ="select new map(fkey as fkey,fvalue as fvalue) from TSyscode where fkey = ?"; 
		String sf = null;
		List<Map<String, String>> list = this.getHibernateTemplate().find(hql, fkey);
		System.out.println(list);
		for(Map<String, String> m:list){
		   sf = m.get("fvalue");
		}
		return sf;
	}
	
	
	
}