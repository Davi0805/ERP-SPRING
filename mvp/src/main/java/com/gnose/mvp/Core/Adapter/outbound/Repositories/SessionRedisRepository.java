package com.gnose.mvp.Core.Adapter.outbound.Repositories;

import com.gnose.mvp.Core.Adapter.outbound.Repositories.DTO.SessionRedisDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRedisRepository extends CrudRepository<SessionRedisDTO, String> {
}
