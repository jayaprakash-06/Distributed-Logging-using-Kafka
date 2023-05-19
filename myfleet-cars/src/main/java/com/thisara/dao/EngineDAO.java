package com.thisara.dao;

import com.thisara.dao.exception.DAOException;
import com.thisara.domain.Engine;


public interface EngineDAO {

	public void save(Engine engine) throws DAOException;
}
