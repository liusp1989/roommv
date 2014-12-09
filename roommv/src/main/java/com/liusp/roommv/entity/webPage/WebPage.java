package com.liusp.roommv.entity.webPage;

import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.SQLException;

import com.liusp.roommv.visitor.IWebPageVisitor;

public class WebPage {

	private String id;

	private Blob headers;

	private String text;

	private Long status;

	private Blob markers;

	private Blob parseStatus;

	private Long modifiedTime;

	private Long prevModifiedTime;

	private Float score;

	private String typ;

	private String batchId;

	private String baseUrl;

	private Blob content;

	private String title;

	private String reprUrl;

	private Long fetchInterval;

	private Long prevFetchTime;

	private Blob inlinks;

	private Blob prevSignature;

	private Blob outlinks;

	private Long fetchTime;

	private Long retriesSinceFetch;

	private Blob protocolStatus;

	private Blob signature;

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
