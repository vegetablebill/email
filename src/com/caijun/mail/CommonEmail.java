// Copyright (c) 2003-present, Jodd Team (http://jodd.org)
// All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are permitted provided that the following conditions are met:
//
// 1. Redistributions of source code must retain the above copyright notice,
// this list of conditions and the following disclaimer.
//
// 2. Redistributions in binary form must reproduce the above copyright
// notice, this list of conditions and the following disclaimer in the
// documentation and/or other materials provided with the distribution.
//
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
// AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
// IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
// ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
// LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
// CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
// SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
// INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
// CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
// ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
// POSSIBILITY OF SUCH DAMAGE.

package com.caijun.mail;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.caijun.utils.array.ArraysUtil;

/**
 * Common stuff for both {@link Email} and {@link jodd.mail.ReceivedEmail}
 */
abstract class CommonEmail {
	public static final String X_PRIORITY = "X-Priority";

	public static final int PRIORITY_HIGHEST = 1;
	public static final int PRIORITY_HIGH = 2;
	public static final int PRIORITY_NORMAL = 3;
	public static final int PRIORITY_LOW = 4;
	public static final int PRIORITY_LOWEST = 5;

	// ---------------------------------------------------------------- from

	protected MailAddress from;

	/**
	 * Sets the FROM address.
	 */
	public void setFrom(MailAddress from) {
		this.from = from;
	}

	/**
	 * Returns FROM {@link MailAddress address}.
	 */
	public MailAddress getFrom() {
		return from;
	}

	// ---------------------------------------------------------------- to

	protected MailAddress[] to = MailAddress.EMPTY_ARRAY;

	/**
	 * Sets TO addresses.
	 */
	public void setTo(MailAddress... tos) {
		if (tos == null) {
			tos = MailAddress.EMPTY_ARRAY;
		}
		to = tos;
	}

	/**
	 * Appends TO address.
	 */
	public void addTo(MailAddress to) {
		this.to = ArraysUtil.append(this.to, to);
	}

	/**
	 * Returns TO addresses.
	 */
	public MailAddress[] getTo() {
		return to;
	}

	// ---------------------------------------------------------------- reply-to

	protected MailAddress[] replyTo = MailAddress.EMPTY_ARRAY;

	/**
	 * Sets REPLY-TO addresses.
	 */
	public void setReplyTo(MailAddress... replyTo) {
		if (replyTo == null) {
			replyTo = MailAddress.EMPTY_ARRAY;
		}
		this.replyTo = replyTo;
	}

	/**
	 * Appends REPLY-TO address.
	 */
	public void addReplyTo(MailAddress to) {
		this.replyTo = ArraysUtil.append(this.replyTo, to);
	}

	/**
	 * Returns REPLY-TO addresses.
	 */
	public MailAddress[] getReplyTo() {
		return replyTo;
	}

	// ---------------------------------------------------------------- cc

	protected MailAddress[] cc = MailAddress.EMPTY_ARRAY;

	/**
	 * Sets CC addresses.
	 */
	public void setCc(MailAddress... ccs) {
		if (ccs == null) {
			ccs = MailAddress.EMPTY_ARRAY;
		}
		cc = ccs;
	}

	/**
	 * Appends CC address.
	 */
	public void addCc(MailAddress to) {
		this.cc = ArraysUtil.append(this.cc, to);
	}

	/**
	 * Returns CC addresses.
	 */
	public MailAddress[] getCc() {
		return cc;
	}

	// ---------------------------------------------------------------- bcc

	protected MailAddress[] bcc = MailAddress.EMPTY_ARRAY;

	/**
	 * Sets BCC addresses.
	 */
	public void setBcc(MailAddress... bccs) {
		if (bccs == null) {
			bccs = MailAddress.EMPTY_ARRAY;
		}
		bcc = bccs;
	}

	/**
	 * Appends BCC address.
	 */
	public void addBcc(MailAddress to) {
		this.bcc = ArraysUtil.append(this.bcc, to);
	}

	/**
	 * Returns BCC addresses.
	 */
	public MailAddress[] getBcc() {
		return bcc;
	}

	// ---------------------------------------------------------------- subject

	protected String subject;
	protected String subjectEncoding;

	/**
	 * Sets message subject.
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * Sets message subject with specified encoding to override default platform
	 * encoding. If the subject contains non US-ASCII characters, it will be
	 * encoded using the specified charset. If the subject contains only
	 * US-ASCII characters, no encoding is done and it is used as-is. The
	 * application must ensure that the subject does not contain any line
	 * breaks. See
	 * {@link javax.mail.internet.MimeMessage#setSubject(String, String)}.
	 */
	public void setSubject(String subject, String encoding) {
		this.subject = subject;
		this.subjectEncoding = encoding;
	}

	/**
	 * Returns message subject.
	 */
	public String getSubject() {
		return this.subject;
	}

	/**
	 * Returns the message subject encoding.
	 */
	public String getSubjectEncoding() {
		return this.subjectEncoding;
	}

	// ---------------------------------------------------------------- message

	protected List<EmailMessage> messages = new ArrayList<EmailMessage>();

	/**
	 * Returns all messages.
	 */
	public List<EmailMessage> getAllMessages() {
		return messages;
	}

	public void addMessage(EmailMessage emailMessage) {
		messages.add(emailMessage);
	}

	public void addMessage(String text, String mimeType, String encoding) {
		messages.add(new EmailMessage(text, mimeType, encoding));
	}

	public void addMessage(String text, String mimeType) {
		messages.add(new EmailMessage(text, mimeType));
	}

	// ---------------------------------------------------------------- headers

	protected Map<String, String> headers;

	/**
	 * Returns all headers as a <code>HashMap</code>.
	 */
	protected Map<String, String> getAllHeaders() {
		return headers;
	}

	/**
	 * Sets a new header value.
	 */
	public void setHeader(String name, String value) {
		if (headers == null) {
			headers = new HashMap<String, String>();
		}
		headers.put(name, value);
	}

	public String getHeader(String name) {
		if (headers == null) {
			return null;
		}
		return headers.get(name);
	}

	/**
	 * Sets email priority. Values of 1 through 5 are acceptable, with 1 being
	 * the highest priority, 3 = normal and 5 = lowest priority.
	 */
	public void setPriority(int priority) {
		setHeader(X_PRIORITY, String.valueOf(priority));
	}

	/**
	 * Returns emails priority (1 - 5) or <code>-1</code> if priority not
	 * available.
	 * 
	 * @see #setPriority(int)
	 */
	public int getPriority() {
		if (headers == null) {
			return -1;
		}
		try {
			return Integer.parseInt(headers.get(X_PRIORITY));
		} catch (NumberFormatException ignore) {
			return -1;
		}
	}

	// ---------------------------------------------------------------- date

	protected Date sentDate;

	/**
	 * Sets e-mails sent date. If input parameter is <code>null</code> then date
	 * will be when email is physically sent.
	 */
	public void setSentDate(Date date) {
		sentDate = date;
	}

	/**
	 * Returns e-mails sent date. If return value is <code>null</code> then date
	 * will be set during the process of sending.
	 * 
	 * @return email's sent date or null if it will be set later.
	 */
	public Date getSentDate() {
		return sentDate;
	}

}
