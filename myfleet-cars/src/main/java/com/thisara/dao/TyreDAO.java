package com.thisara.dao;

import com.thisara.dao.exception.DAOException;
import com.thisara.domain.Tyre;


public interface TyreDAO {

	public void save(Tyre tyre) throws DAOException;
}
