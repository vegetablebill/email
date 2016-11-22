package com.caijun.mail.att;

import com.caijun.mail.EmailAttachment;

import javax.activation.DataSource;

/**
 * Generic <code>DataSource</code> adapter for attachments.
 */
public class DataSourceAttachment extends EmailAttachment {

	protected final DataSource dataSource;

	public DataSourceAttachment(DataSource dataSource, String name, String contentId) {
		super(name, contentId);
		this.dataSource = dataSource;
	}

	/**
	 * Returns wrapped data source.
	 */
	@Override
	public DataSource getDataSource() {
		return dataSource;
	}
}
