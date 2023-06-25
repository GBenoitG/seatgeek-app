package fr.bendev.seatgeekapp.framework.di

import android.app.Application
import fr.bendev.seatgeekapp.framework.network.SeatGeekNetwork

/**
 * Injector is here to have instances of many component inside the app. We can use it for field
 * injection and constructor injection.
 */
object Injector {

    /**
     * Context
     * */
    lateinit var application: Application
        private set

    /**
     * Network
     */
    private lateinit var network: SeatGeekNetwork

    /**
     * DAOs
     * DESCRIPTION: List of instance of DAOs with lazy loading.
     * USAGE: It will depends on which Database system will be used: Realm or Rooms.
     * NAMING CONVENTION (for Realm): val {object}Dao: {Object}Dao by lazy { {Object}Dao() }
     * NAMING CONVENTION (for Room): val {object}Dao: {Object}Dao by lazy { databaseInstance.sampleDao }
     */


    /**
     * DataSources
     * DESCRIPTION: List of instance of data source with lazy loading.
     * NAMING CONVENTION: val {object}DataSource: {Object}RemoteDataSource by lazy { {Class}DataSource }
     */



    /**
     * Repositories
     * DESCRIPTION: List of instance of repositories with lazy loading.
     * NAMING CONVENTION: val {object}Repository: {Object}Repository by lazy { {Object}DataSourceImpl }
    */


     /**/

    /**
     * SharedPreferences
     * DESCRIPTION: Instance of SharedPreferences class with mazy loading.
     * NAMING CONVENTION: val {object}SharePreferences: SharedPreferences by lazy { {Object}SharedPrefs }

        val sampleSharePreferences: SharedPreferences by lazy { SampleSharedPrefs }

     */


    /**
     * Based on Builder pattern
     */
    fun initNetwork(builder: SeatGeekNetwork.Builder.() -> SeatGeekNetwork.Builder) = apply {
        network = builder(SeatGeekNetwork.Builder())
            .build()
    }

    fun build(application: Application) {
        this.application = application
    }

}