package ru.mail.park.java.sample22jdbcspring;

import org.springframework.beans.factory.annotation.Autowired;

class SampleSpringDataDaoTest extends SampleDaoTest {

	@Autowired
	private SampleSpringDataDao dao;

	@Override
	protected SampleDao getDao() {
		return dao;
	}

}
