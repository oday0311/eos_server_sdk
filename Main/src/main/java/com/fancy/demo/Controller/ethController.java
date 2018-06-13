package com.fancy.demo.Controller;

import com.fancyios.eoswallet.data.EoscDataManager;
import com.fancyios.eoswallet.data.remote.model.api.EosChainInfo;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

@RestController
public class ethController {

    private final Logger log = LoggerFactory.getLogger(ethController.class);

    String ethUrl = "https://mainnet.infura.io/metamask";
    //String ethUrl =  "https://morden.infura.io/9bDocwMoLNdrtkODxqv0";
    //String ethUrl = "https://rinkeby.infura.io/9bDocwMoLNdrtkODxqv0";

    @RequestMapping("/eth/fancy")
    public Object getFancy()
    {

        getClientVersion();
        return "hello world";
    }




    void getClientVersion()
    {
        try {
            Web3j web3 = Web3j.build(new HttpService(ethUrl));  // defaults to http://localhost:8545/
            Web3ClientVersion web3ClientVersion = web3.web3ClientVersion().send();
            String clientVersion = web3ClientVersion.getWeb3ClientVersion();
            log.info("alex_huang", clientVersion);
        }catch (Exception e)
        {
            log.info(e.toString());
        }


    }



}
