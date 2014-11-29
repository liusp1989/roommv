package com.liusp.roommv.entity.webPage;



import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.SQLException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.liusp.roommv.visitor.IWebPageVisitor;


@Entity
@Table(name = "webpage")
public class WebPage {
	@Id
	@Column(name = "id")
	private String id;
	@Column(name = "headers")
	private Blob headers;
	@Column(name = "text")
	private String text;
	@Column(name = "status")
	private Long status;
	@Column(name = "markers")
	private Blob markers;
	@Column(name = "parseStatus")
	private Blob parseStatus;
	@Column(name = "modifiedTime")
	private Long modifiedTime;
	@Column(name = "prevModifiedTime")
	private Long prevModifiedTime;
	@Column(name = "score")
	private Float score;
	@Column(name = "typ")
	private String typ;
	@Column(name = "batchId")
	private String batchId;
	@Column(name = "baseUrl")
	private String baseUrl;
	@Column(name = "content")
	private Blob content;
	@Column(name = "title")
	private String title;
	@Column(name = "reprUrl")
	private String reprUrl;
	@Column(name = "fetchInterval")
	private Long fetchInterval;
	@Column(name = "prevFetchTime")
	private Long prevFetchTime;
	@Column(name = "inlinks")
	private Blob inlinks;
	@Column(name = "prevSignature")
	private Blob prevSignature;
	@Column(name = "outlinks")
	private Blob outlinks;
	@Column(name = "fetchTime")
	private Long fetchTime;
	@Column(name = "retriesSinceFetch")
	private Long retriesSinceFetch;
	@Column(name = "protocolStatus")
	private Blob protocolStatus;
	@Column(name = "signature")
	private Blob signature;
	@Column(name = "metadata")
	private Blob metadata;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Blob getHeaders() {
		return headers;
	}
	public void setHeaders(Blob headers) {
		this.headers = headers;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}
	public Blob getMarkers() {
		return markers;
	}
	public void setMarkers(Blob markers) {
		this.markers = markers;
	}
	public Blob getParseStatus() {
		return parseStatus;
	}
	public void setParseStatus(Blob parseStatus) {
		this.parseStatus = parseStatus;
	}

	public Long getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Long modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public Long getPrevModifiedTime() {
		return prevModifiedTime;
	}

	public void setPrevModifiedTime(Long prevModifiedTime) {
		this.prevModifiedTime = prevModifiedTime;
	}
	public Float getScore() {
		return score;
	}
	public void setScore(Float score) {
		this.score = score;
	}
	public String getTyp() {
		return typ;
	}
	public void setTyp(String typ) {
		this.typ = typ;
	}
	public String getBatchId() {
		return batchId;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	public String getBaseUrl() {
		return baseUrl;
	}
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}
	public Blob getContent() {
		return content;
	}
	public void setContent(Blob content) {
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getReprUrl() {
		return reprUrl;
	}
	public void setReprUrl(String reprUrl) {
		this.reprUrl = reprUrl;
	}

	public Long getFetchInterval() {
		return fetchInterval;
	}

	public void setFetchInterval(Long fetchInterval) {
		this.fetchInterval = fetchInterval;
	}

	public Long getPrevFetchTime() {
		return prevFetchTime;
	}

	public void setPrevFetchTime(Long prevFetchTime) {
		this.prevFetchTime = prevFetchTime;
	}
	public Blob getInlinks() {
		return inlinks;
	}
	public void setInlinks(Blob inlinks) {
		this.inlinks = inlinks;
	}
	public Blob getPrevSignature() {
		return prevSignature;
	}
	public void setPrevSignature(Blob prevSignature) {
		this.prevSignature = prevSignature;
	}
	public Blob getOutlinks() {
		return outlinks;
	}
	public void setOutlinks(Blob outlinks) {
		this.outlinks = outlinks;
	}

	public Long getFetchTime() {
		return fetchTime;
	}

	public void setFetchTime(Long fetchTime) {
		this.fetchTime = fetchTime;
	}

	public Long getRetriesSinceFetch() {
		return retriesSinceFetch;
	}

	public void setRetriesSinceFetch(Long retriesSinceFetch) {
		this.retriesSinceFetch = retriesSinceFetch;
	}
	public Blob getProtocolStatus() {
		return protocolStatus;
	}
	public void setProtocolStatus(Blob protocolStatus) {
		this.protocolStatus = protocolStatus;
	}
	public Blob getSignature() {
		return signature;
	}
	public void setSignature(Blob signature) {
		this.signature = signature;
	}
	public Blob getMetadata() {
		return metadata;
	}
	public void setMetadata(Blob metadata) {
		this.metadata = metadata;
	}

	public void accept(IWebPageVisitor visitor) throws SQLException,
			UnsupportedEncodingException {
		visitor.visit(this);
	}
}
