package com.gnose.mvp.Core.Adapter.outbound;

import com.gnose.mvp.Core.Adapter.outbound.DTO.SessionRedisDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRedisRepository extends CrudRepository<SessionRedisDTO, String> {
}
