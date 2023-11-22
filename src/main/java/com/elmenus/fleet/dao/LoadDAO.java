package com.elmenus.fleet.dao;

import com.elmenus.fleet.entity.Load;

public interface LoadDAO {

    Load save(Load load);

    Load findLoadByIdJoinFetch(Long id);

}
