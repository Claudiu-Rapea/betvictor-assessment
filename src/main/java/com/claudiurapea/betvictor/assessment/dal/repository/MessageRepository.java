package com.claudiurapea.betvictor.assessment.dal.repository;

import com.claudiurapea.betvictor.assessment.dal.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MessageRepository extends JpaRepository<MessageEntity, UUID> {
}
