package cn.hbook.Junit.test;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import cn.hbook.form.QueryBookInfo;


public class Test {
	public static void main(String[] args) {
		Configuration cf = new Configuration();
		cf.configure();
		Session s = cf.buildSessionFactory().openSession();
		//按ID查询可以
	/*	String hql ="select log from TLog log join log.TUser u  where u.id = ? ";
		Query q = s.createQuery(hql);
		q.setParameter(0, 5);
		List<TLog> logs = q.list();
		for (TLog log : logs) {
			System.out.println(log.getInfo() + " : " + log.getTUser().getRealName());
		}*/
		
//		System.out.println("按姓名");
//		//String hql ="select new map(log.info as info, u.realName as realName) from TLog log, TUser u where  u.realName like " + "'%大宝%'" + " and u.id = log.TUser.id ";
//		String hql = "select log from TLog log join log.TUser u where 1 = 1  and u.userName like ?";
//		Query q = s.createQuery(hql);
//		q.setParameter(0, "%gbx%");
//		List<TLog> logs = q.list();
//		for (TLog log : logs) {
//			System.out.println(log.getInfo() + " : " + log.getTUser().getRealName());
//		}
		/*StringBuilder hql = new StringBuilder("select count(*) from TOrderdetail od where od.TUser.userName = ?");
		Query q = s.createQuery(hql.toString());
		q.setParameter(0, "gbx");
		System.out.println(q.uniqueResult());*/
//		select b from TBook b join b.TAuthors a where a.name like ?
		
	
		/*StringBuilder hql = new StringBuilder(" select distinct b from TBook b join b.TAuthors a where 1 = 1   " );
		Query q = s.createQuery(hql.toString());
		//q.setParameter(0, "gbx");
		//q.setParameter(1, 1);
		List<TBook> books = q.list();
		for (TBook b : books) {
			System.out.println(b.getId() + ":" + b.getName()); 
			for (Object obj : b.getTAuthors()) {
				TAuthor a = (TAuthor)obj;
				System.out.println(a.getName());
			}
			System.out.println("---------------------------");
		}
		*/
		
		/*StringBuilder hql = new StringBuilder(" select distinct b from TBook b join b.TAuthors a where b.stockNum > b.saleNum  order by b.grade desc limit 0, 5" );
		
	    Query q = s.createQuery(hql.toString());
	   // q.setParameter(0, 1);
	    List<TBook> newBooks = q.list();
		
		for (TBook b : newBooks) {
			System.out.println(b.getPicture() + b.getName() + b.getId() + b.getPublishDate() + b.getUsed());
		}*/
		
		
		QueryBookInfo q = new QueryBookInfo();
		System.out.println(q.getUsed());
	}

}
