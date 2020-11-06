package com.example.imdb.model;

import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class JaxbList<T> {
	protected Collection<T> list;

	public JaxbList() {
	}

	public JaxbList(Collection<T> list) {
		this.list = list;
	}

	@XmlElement(name = "Item")
	public Collection<T> getList() {
		return list;
	}
}
