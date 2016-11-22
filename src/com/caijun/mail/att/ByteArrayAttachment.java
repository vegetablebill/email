package com.caijun.mail.att;

import javax.activation.DataSource;
import javax.mail.util.ByteArrayDataSource;

import com.caijun.mail.EmailAttachment;

/**
 * Byte array {@link EmailAttachment email attachment}.
 */
public class ByteArrayAttachment extends EmailAttachment {

	protected final byte[] content;
	protected final String contentType;

	public ByteArrayAttachment(byte[] content, String contentType, String name,
			String contentId) {
		super(name, contentId);
		this.content = content;
		this.contentType = contentType;
	}

	/**
	 * Returns <code>ByteArrayDataSource</code>.
	 */
	@Override
	public DataSource getDataSource() {
		return new ByteArrayDataSource(content, contentType);
	}

	/**
	 * Returns content type.
	 */
	public String getContentType() {
		return contentType;
	}

}