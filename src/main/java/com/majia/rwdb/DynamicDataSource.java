package com.majia.rwdb;

import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource{
	    private Object writeDataSource; //д���Դ

	    private Object readDataSource; //�����Դ

	    @Override
	    public void afterPropertiesSet() {
	        if (this.writeDataSource == null) {
	            throw new IllegalArgumentException("Property 'writeDataSource' is required");
	        }
	        setDefaultTargetDataSource(writeDataSource);
	        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
	        targetDataSources.put(DynamicDataSourceGlobal.WRITE.name(), writeDataSource);
	        if(readDataSource != null) {
	            targetDataSources.put(DynamicDataSourceGlobal.READ.name(), readDataSource);
	        }
	        setTargetDataSources(targetDataSources);
	        super.afterPropertiesSet();
	    }

	    @Override
	    protected Object determineCurrentLookupKey() {

	        DynamicDataSourceGlobal dynamicDataSourceGlobal = DynamicDataSourceHolder.getDataSource();

	        if(dynamicDataSourceGlobal == null
	                || dynamicDataSourceGlobal == DynamicDataSourceGlobal.WRITE) {
	            return DynamicDataSourceGlobal.WRITE.name();
	        }

	        return DynamicDataSourceGlobal.READ.name();
	    }

	    public void setWriteDataSource(Object writeDataSource) {
	        this.writeDataSource = writeDataSource;
	    }

	    public Object getWriteDataSource() {
	        return writeDataSource;
	    }

	    public Object getReadDataSource() {
	        return readDataSource;
	    }

	    public void setReadDataSource(Object readDataSource) {
	        this.readDataSource = readDataSource;
	    }
}
