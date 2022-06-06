package com.claudiurapea.betvictor.assessment.dal.entity;

import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "MESSAGE")
@EntityListeners(AuditingEntityListener.class)
public class MessageEntity {

    @Id
    @GeneratedValue
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "CREATED_DATE")
    @CreatedDate
    private LocalDateTime createdDateTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof MessageEntity))
            return false;

        MessageEntity that = (MessageEntity) o;

        return id != null && id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

}
