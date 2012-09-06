package com.napramirez.igno.server.dbcp.bonecp;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.jpos.q2.QBeanSupport;
import org.jpos.util.NameRegistrar;

import com.jolbox.bonecp.BoneCPConfig;
import com.jolbox.bonecp.BoneCPDataSource;

/**
 * QBoneCPDataSource wraps the BoneCPDataSource so it may be used as a Q2 component. 
 * 
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public class QBoneCPDataSource
    extends QBeanSupport
    implements DataSource
{
    private static final String NAME_PREFIX = "connection.pool.";

    private static final String PROPERTY_JDBC_DRIVER_CLASS = "jdbc.driver";

    private static final String PROPERTY_JDBC_URL = "jdbc.url";

    private static final String PROPERTY_JDBC_USER = "jdbc.user";

    private static final String PROPERTY_JDBC_PASSWORD = "jdbc.password";

    private static final String PROPERTY_PARTITION_COUNT = "partition-count";

    private static final String PROPERTY_MIN_CONNECTIONS_PER_PARTITION = "min-connections-per-partition";

    private static final String PROPERTY_MAX_CONNECTIONS_PER_PARTITION = "max-connections-per-partition";

    private static final String PROPERTY_ACQUIRE_INCREMENT = "acquire-increment";

    private static final String PROPERTY_STATEMENTS_CACHE_SIZE = "statements-cache-size";

    private static final String PROPERTY_RELEASE_HELPER_THREADS = "release-helper-threads";

    private static final String PROPERTY_CONNECTION_TEST_STATEMENT = "connection-test-statement";

    private static final int DEFAULT_PARTITION_COUNT = 1;

    private static final int DEFAULT_MIN_CONNECTIONS_PER_PARTITION = 3;

    private static final int DEFAULT_MAX_CONNECTIONS_PER_PARTITION = 15;

    private static final int DEFAULT_ACQUIRE_INCREMENT = 3;

    private static final int DEFAULT_STATEMENTS_CACHE_SIZE = 0;

    private static final int DEFAULT_RELEASE_HELPER_THREADS = 1;

    private BoneCPDataSource ds;

    @Override
    protected void initService()
        throws Exception
    {
        Class.forName( cfg.get( PROPERTY_JDBC_DRIVER_CLASS ) );

        BoneCPConfig config = new BoneCPConfig();
        config.setJdbcUrl( cfg.get( PROPERTY_JDBC_URL ) );
        config.setUsername( cfg.get( PROPERTY_JDBC_USER ) );
        config.setPassword( cfg.get( PROPERTY_JDBC_PASSWORD ) );

        config.setPartitionCount( cfg.getInt( PROPERTY_PARTITION_COUNT, DEFAULT_PARTITION_COUNT ) );
        config.setMinConnectionsPerPartition( cfg.getInt( PROPERTY_MIN_CONNECTIONS_PER_PARTITION,
                                                          DEFAULT_MIN_CONNECTIONS_PER_PARTITION ) );
        config.setMaxConnectionsPerPartition( cfg.getInt( PROPERTY_MAX_CONNECTIONS_PER_PARTITION,
                                                          DEFAULT_MAX_CONNECTIONS_PER_PARTITION ) );

        config.setAcquireIncrement( cfg.getInt( PROPERTY_ACQUIRE_INCREMENT, DEFAULT_ACQUIRE_INCREMENT ) );
        config.setStatementsCacheSize( cfg.getInt( PROPERTY_STATEMENTS_CACHE_SIZE, DEFAULT_STATEMENTS_CACHE_SIZE ) );
        config.setReleaseHelperThreads( cfg.getInt( PROPERTY_RELEASE_HELPER_THREADS, DEFAULT_RELEASE_HELPER_THREADS ) );

        config.setConnectionTestStatement( cfg.get( PROPERTY_CONNECTION_TEST_STATEMENT ) );

        ds = new BoneCPDataSource( config );

        NameRegistrar.register( NAME_PREFIX + getName(), this );
    }

    @Override
    protected void destroyService()
        throws Exception
    {
        NameRegistrar.unregister( NAME_PREFIX + getName() );
    }

    public static DataSource getConnectionPool( String name )
        throws NameRegistrar.NotFoundException
    {
        return (DataSource) NameRegistrar.get( NAME_PREFIX + name );
    }

    public Connection getConnection()
        throws SQLException
    {
        return ds.getConnection();
    }

    public Connection getConnection( String username, String password )
        throws SQLException
    {
        return ds.getConnection( username, password );
    }

    public PrintWriter getLogWriter()
        throws SQLException
    {
        return ds.getLogWriter();
    }

    public void setLogWriter( PrintWriter out )
        throws SQLException
    {
        ds.setLogWriter( out );
    }

    public void setLoginTimeout( int seconds )
        throws SQLException
    {
        ds.setLoginTimeout( seconds );
    }

    public int getLoginTimeout()
        throws SQLException
    {
        return ds.getLoginTimeout();
    }

    @SuppressWarnings( "unchecked" )
    public <T> T unwrap( Class<T> iface )
        throws SQLException
    {
        return (T) ds.unwrap( iface );
    }

    public boolean isWrapperFor( Class<?> iface )
        throws SQLException
    {
        return ds.isWrapperFor( iface );
    }
}
