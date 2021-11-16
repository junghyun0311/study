package com.example.demo.VO;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import com.example.demo.Util.DateJsonSerializer;

public class CommonVO {
	private Date crtDate;
	private Date uptDate;
	private int pageIndex; 
    private int startRow; 
    private int endRow;
    private int pageSize; 
    private int defaultSize = 10; 

	/**
	 * @return the defaultSize
	 */
	public int getDefaultSize() {
		return defaultSize;
	}

	/**
	 * @param defaultSize the defaultSize to set
	 */
	public void setDefaultSize(int defaultSize) {
		this.defaultSize = defaultSize;
	}

	/**
	 * @param startRow the startRow to set
	 */
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	/**
	 * @param endRow the endRow to set
	 */
	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	/**
	 * 
	 */
	public CommonVO() {
		super();
		handlePaging(0, defaultSize);
	}

	/**
	 * @param pageIndex
	 */
	public CommonVO(int pageIndex) {
		super();
		handlePaging(pageIndex, defaultSize);
	}

    /**
	 * @param pageIndex
	 * @param pageSize
	 */
	public CommonVO(int pageIndex, int pageSize) {
		super();
		handlePaging(pageIndex, pageSize);
	}

	/**
     * @methodName    : handlePaging
     * @author        : Kenny Kim
     * @date          : 2020.10.23
     * @param pageIndex
     * @param pageSize
     * startRow, endRow를 계산한다. 
     */
    public void handlePaging(int pageIndex, int pageSize) {
        this.pageIndex = pageIndex < 1 ? 1 : pageIndex;
        this.pageSize = pageSize;
        this.startRow = ((this.pageIndex-1) * pageSize);
        this.endRow = startRow + pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public int getStartRow() {
        return startRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public int getPageSize() {
        return pageSize;
    }
    
	/**
	 * @param pageIndex the pageIndex to set
	 */
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
		handlePaging(pageIndex, this.pageSize);
	}

	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		handlePaging(this.pageIndex, this.pageSize);
	}

	/**
	 * @return the crtDate
	 */
	@JsonSerialize(using = DateJsonSerializer.class)
	public Date getCrtDate() {
		return crtDate;
	}

	/**
	 * @param crtDate the crtDate to set
	 */
	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
	}

	/**
	 * @return the uptDate
	 */
	@JsonSerialize(using = DateJsonSerializer.class)
	public Date getUptDate() {
		return uptDate;
	}

	/**
	 * @param uptDate the uptDate to set
	 */
	public void setUptDate(Date uptDate) {
		this.uptDate = uptDate;
	}
}
