package com.fancy.demo.Controller;

import com.fancy.demo.Common.Command;
import com.fancy.demo.Common.ShellTest;
import com.fancy.demo.Service.FancyBaseDataService;
import com.fancy.demo.Service.MBKUserFreeChargeService;
import com.fancyios.eoswallet.crypto.ec.EosPrivateKey;
import com.fancyios.eoswallet.data.EoscDataManager;
import com.fancyios.eoswallet.data.local.db.AppDatabase;
import com.fancyios.eoswallet.data.local.repository.EosAccountRepository;
import com.fancyios.eoswallet.data.local.repository.EosAccountRepositoryImpl;
import com.fancyios.eoswallet.data.prefs.PreferencesHelper;
import com.fancyios.eoswallet.data.remote.HostInterceptor;
import com.fancyios.eoswallet.data.remote.NodeosApi;
import com.fancyios.eoswallet.data.remote.model.abi.EosAbiMain;
import com.fancyios.eoswallet.data.remote.model.api.EosChainInfo;
import com.fancyios.eoswallet.data.remote.model.api.PushTxnResponse;
import com.fancyios.eoswallet.data.util.GsonEosTypeAdapterFactory;
import com.fancyios.eoswallet.data.wallet.EosWalletManager;
import com.fancyios.eoswallet.util.ToastUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;




import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Created by alex on 2017/3/4.
 */
@RestController
public class fancyController {
    public  EoscDataManager mDataManager;
    public  HostInterceptor mHostInterceptor;


    public EosWalletManager walletManager;

    public OkHttpClient client;

    public Gson gson;

    public AppDatabase database;

    public NodeosApi nodeosApi;

    public PreferencesHelper preferencesHelper;
    public EosAccountRepository accountRepository;


    public String serverUrl = "http://47.91.247.187:8099";



    private final Logger log = LoggerFactory.getLogger(fancyController.class);

    @Resource
    JdbcTemplate jdbcTemplate;

    @Resource
    Environment environment;

    //@Value("${spring.datasource.url}")
    String url;

    //@Value("${mobike.api.domain-name}")
    String domainName;


    @Autowired
    private MBKUserFreeChargeService mbkUserFreeChargeService;

    @Autowired
    private FancyBaseDataService fancyBaseDataService;


    public void buildAppDataBase()
    {
        database = null; // Room.databaseBuilder( this.getApplicationContext(), AppDatabase.class, "eosc.db").build();


        accountRepository = new EosAccountRepositoryImpl(database);

    }
    public void buildHttpClient()
    {
        //47.91.247.187:8099
        mHostInterceptor = new HostInterceptor();
        mHostInterceptor.setInterceptor("http", "47.91.247.187" , 8099);

        gson = new GsonBuilder()
                .registerTypeAdapterFactory(new GsonEosTypeAdapterFactory())
                .serializeNulls()
                .excludeFieldsWithoutExposeAnnotation().create();

        //日志显示级别
        HttpLoggingInterceptor.Level level= HttpLoggingInterceptor.Level.BODY;
        //新建log拦截器
        HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {

                log.info("alex_huang" + "OkHttp====Message:"+message);
            }
        });
        loggingInterceptor.setLevel(level);
        client = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(mHostInterceptor)
                .addInterceptor(loggingInterceptor)
                .build();





        String url = serverUrl;


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl( url )
                .addConverterFactory( GsonConverterFactory.create( gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // retrofit 용 rxjava2 adapter
                .client( client )
                .build();

        nodeosApi = retrofit.create( NodeosApi.class);



    }

    public void buildWalletManager()
    {


        walletManager = new EosWalletManager();

        preferencesHelper = new PreferencesHelper();


    }


    @RequestMapping("/fancy")
    public Object getFancy()
    {


        buildAppDataBase();
        buildHttpClient();

        buildWalletManager();


        mDataManager = new EoscDataManager(nodeosApi,walletManager,accountRepository,preferencesHelper);

        mDataManager.getChainInfo().subscribe(new Observer<EosChainInfo>() {
            @Override
            public void onSubscribe(Disposable disposable) {

            }

            @Override
            public void onNext(EosChainInfo eosChainInfo) {
                log.info("alex_huang" + eosChainInfo.getBrief());
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {

            }
        });

        return "hello world";
    }





    public void getTableInfo()
    {
        String code = "alex";
        String table = "fancyad";
        String tableKey = "";
        String lowBound = "";
        String upperBound = "";
        int limit = 10;

        mDataManager.getTable("alex",code,table,tableKey,lowBound,upperBound,limit).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                        log.info("alex_huang" + "get table : " + s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }


    public void pushActionToContract()
    {

        String[] permission = new String[1];
        permission[0] = new String("alex@active");

        String data = "{\"user\":\"huangzhifang\"}";

        //mDataManager.
        mDataManager.pushAction("alex","hi",data,permission).subscribe(new Observer<PushTxnResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(PushTxnResponse pushTxnResponse) {

                        log.info("alex_huang" + "push action : " + pushTxnResponse.toString());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }


    public void getCodeABI()
    {
        mDataManager.getCodeAbi("alex").subscribe(new Observer<EosAbiMain>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(EosAbiMain eosAbiMain) {

                        log.info("alex_huang" + "get code abi : " + eosAbiMain.toString());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }


    public void transferEPRA()
    {
        mDataManager.transfer("alex","huang",8,"M").subscribe(new Observer<JsonObject>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(JsonObject jsonObject) {
                log.info("alex_huang" + "transfer  " + jsonObject.toString());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void getAccountBalance()
    {
        mDataManager.getCurrencyBalance("eosio.token","alex","EPRA").subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                log.info("alex_huang" + "get balance  " + s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
    public void getAccountTransaction()
    {
        mDataManager.getTransactions("eosio").subscribe(new Observer<JsonObject>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(JsonObject jsonObject) {


            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void getAccountServants(){
        mDataManager.getServants("eosio").subscribe(new Observer<JsonObject>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(JsonObject jsonObject) {


            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void generateKeys()
    {
        mDataManager.createKey(1).subscribe(new Observer<EosPrivateKey[]>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(EosPrivateKey[] eosPrivateKeys) {


            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
    public void getAccountInfo()
    {
        mDataManager.readAccountInfo("alex").subscribe(new Observer<JsonObject>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(JsonObject ob) {

                    log.info("alex_huang" + ob.toString());



            }

            @Override
            public void onError(Throwable e) {
                log.error("alex huang" + "the EOS get account error " + e);

            }

            @Override
            public void onComplete() {

            }
        });
    }

}
