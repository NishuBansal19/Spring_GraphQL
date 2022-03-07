package com.adobe.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adobe.demo.entity.Publisher;

public interface PublisherDao extends JpaRepository<Publisher, Integer> {

}
