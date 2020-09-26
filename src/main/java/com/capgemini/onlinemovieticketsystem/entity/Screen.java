package com.capgemini.onlinemovieticketsystem.entity;

import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Screen")
public class Screen {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="screenId")
	private int screenId;

	private int theaterId;

	@Column(name="screenName")
	private String screenName;

	@OneToMany(cascade=CascadeType.PERSIST, orphanRemoval = true)
	private List<Show> showList;

	@Column(name="rows")
	private int rows;

	@Column(name="columns")
	private int columns;

	public Screen() {

	}

	public Screen(int screenId, int theaterId, String screenName, List<Show> showList, int rows1, int columns) {
		super();
		this.screenId = screenId;
		this.theaterId = theaterId;
		this.screenName = screenName;
		this.showList = showList;
		this.rows = rows;
		this.columns = columns;
	}

	public int getScreenId() {
		return screenId;
	}

	public void setScreenId(int screenId) {
		this.screenId = screenId;
	}

	public int getTheatreId() {
		return theaterId;
	}

	public void setTheatreId(int theatreId) {
		this.theaterId = theatreId;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public List<Show> getShowList() {
		return showList;
	}

	public void setShowList(List<Show> showList) {
		this.showList = showList;
	}

	public int getRows1() {
		return rows;
	}

	public void setRows1(int rows1) {
		this.rows = rows1;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

	public Show searchShow(String showName){
		Iterator<Show> it = this.getShowList().iterator();
		while(it.hasNext()){
			Show show = it.next();
			if(show.getShowName().equals(showName)){
				return show;
			}
		}
		return null;
	}

	public void setTheaterId(int theaterId) {
	}

	public Object getTheaterId() {
		return theaterId;
	}

//	public List<Show> showShows(){
//		return this.getShowList();
//	}
//
//    public Object getTheaterId() {
//    }
//
//	public void setRows(int rows) {
//	}
//
//	public void setTheaterId(int theaterId) {
//	}

}
