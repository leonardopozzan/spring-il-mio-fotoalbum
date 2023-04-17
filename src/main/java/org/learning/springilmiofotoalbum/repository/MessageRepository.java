package org.learning.springilmiofotoalbum.repository;

import org.learning.springilmiofotoalbum.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Integer> {
}
