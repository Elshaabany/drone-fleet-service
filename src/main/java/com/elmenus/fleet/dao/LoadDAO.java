package com.elmenus.fleet.dao;

import com.elmenus.fleet.entity.Load;

public interface LoadDAO {

    void save(Load load);

    void update(Load load);

    Load findLoadByIdJoinFetch(Long id);

}
