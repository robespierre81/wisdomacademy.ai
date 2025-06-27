package com.bodiva.wisdomacademy.blockchain;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.10.0.
 */
@SuppressWarnings("rawtypes")
public class CourseManager extends Contract {
    public static final String BINARY = "6080604052348015600e575f5ffd5b506040516104b33803806104b3833981016040819052602b916057565b5f80546001600160a01b039092166001600160a01b031992831617905560018054909116331790556082565b5f602082840312156066575f5ffd5b81516001600160a01b0381168114607b575f5ffd5b9392505050565b6104248061008f5f395ff3fe608060405234801561000f575f5ffd5b506004361061007a575f3560e01c8063a637bef711610058578063a637bef71461010b578063c6530e411461011e578063cd411a9d1461014b578063f6c3372f14610178575f5ffd5b8063194d73a21461007e5780635f6d4b68146100935780638da5cb5b146100e0575b5f5ffd5b61009161008c36600461035c565b61018a565b005b6100cb6100a1366004610373565b6001600160a01b03919091165f908152600360209081526040808320938352929052205460ff1690565b60405190151581526020015b60405180910390f35b6001546100f3906001600160a01b031681565b6040516001600160a01b0390911681526020016100d7565b6100916101193660046103a8565b6102f1565b6100cb61012c366004610373565b600360209081525f928352604080842090915290825290205460ff1681565b61016a61015936600461035c565b60026020525f908152604090205481565b6040519081526020016100d7565b5f546100f3906001600160a01b031681565b5f81815260026020526040902054806101e15760405162461bcd60e51b8152602060048201526014602482015273436f75727365206e6f7420617661696c61626c6560601b60448201526064015b60405180910390fd5b5f546040516323b872dd60e01b8152336004820152306024820152604481018390526001600160a01b03909116906323b872dd906064016020604051808303815f875af1158015610234573d5f5f3e3d5ffd5b505050506040513d601f19601f8201168201806040525081019061025891906103c8565b6102955760405162461bcd60e51b815260206004820152600e60248201526d14185e5b595b9d0819985a5b195960921b60448201526064016101d8565b335f818152600360209081526040808320868452825291829020805460ff1916600117905590518381528492917f4b71ef6d807a9a201a6f28d104a403722af74f85b8f2042a8d152bd2a78f5586910160405180910390a35050565b6001546001600160a01b0316331461034b5760405162461bcd60e51b815260206004820152601960248201527f4f6e6c79206f776e65722063616e20736574207072696365730000000000000060448201526064016101d8565b5f9182526002602052604090912055565b5f6020828403121561036c575f5ffd5b5035919050565b5f5f60408385031215610384575f5ffd5b82356001600160a01b038116811461039a575f5ffd5b946020939093013593505050565b5f5f604083850312156103b9575f5ffd5b50508035926020909101359150565b5f602082840312156103d8575f5ffd5b815180151581146103e7575f5ffd5b939250505056fea2646970667358221220e110032a6e5171105fac85b713e09dec541216607e3552d5afd42a26a9690aa064736f6c634300081d0033";

    public static final String FUNC_BUYCOURSE = "buyCourse";

    public static final String FUNC_COURSEPRICES = "coursePrices";

    public static final String FUNC_HASACCESS = "hasAccess";

    public static final String FUNC_HASBOUGHT = "hasBought";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_SETCOURSEPRICE = "setCoursePrice";

    public static final String FUNC_WSDTOKEN = "wsdToken";

    public static final Event COURSEPURCHASED_EVENT = new Event("CoursePurchased", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected CourseManager(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected CourseManager(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected CourseManager(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected CourseManager(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<CoursePurchasedEventResponse> getCoursePurchasedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(COURSEPURCHASED_EVENT, transactionReceipt);
        ArrayList<CoursePurchasedEventResponse> responses = new ArrayList<CoursePurchasedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            CoursePurchasedEventResponse typedResponse = new CoursePurchasedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.user = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.courseId = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.price = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static CoursePurchasedEventResponse getCoursePurchasedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(COURSEPURCHASED_EVENT, log);
        CoursePurchasedEventResponse typedResponse = new CoursePurchasedEventResponse();
        typedResponse.log = log;
        typedResponse.user = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.courseId = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.price = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<CoursePurchasedEventResponse> coursePurchasedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getCoursePurchasedEventFromLog(log));
    }

    public Flowable<CoursePurchasedEventResponse> coursePurchasedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(COURSEPURCHASED_EVENT));
        return coursePurchasedEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> buyCourse(BigInteger courseId) {
        final Function function = new Function(
                FUNC_BUYCOURSE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(courseId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> coursePrices(BigInteger param0) {
        final Function function = new Function(FUNC_COURSEPRICES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Boolean> hasAccess(String param0, BigInteger param1) {
        final Function function = new Function(FUNC_HASACCESS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0), 
                new org.web3j.abi.datatypes.generated.Uint256(param1)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<Boolean> hasBought(String user, BigInteger courseId) {
        final Function function = new Function(FUNC_HASBOUGHT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, user), 
                new org.web3j.abi.datatypes.generated.Uint256(courseId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<String> owner() {
        final Function function = new Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> setCoursePrice(BigInteger courseId, BigInteger price) {
        final Function function = new Function(
                FUNC_SETCOURSEPRICE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(courseId), 
                new org.web3j.abi.datatypes.generated.Uint256(price)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> wsdToken() {
        final Function function = new Function(FUNC_WSDTOKEN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    @Deprecated
    public static CourseManager load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new CourseManager(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static CourseManager load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new CourseManager(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static CourseManager load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new CourseManager(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static CourseManager load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new CourseManager(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<CourseManager> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String _wsdTokenAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _wsdTokenAddress)));
        return deployRemoteCall(CourseManager.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<CourseManager> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String _wsdTokenAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _wsdTokenAddress)));
        return deployRemoteCall(CourseManager.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<CourseManager> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _wsdTokenAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _wsdTokenAddress)));
        return deployRemoteCall(CourseManager.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<CourseManager> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _wsdTokenAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _wsdTokenAddress)));
        return deployRemoteCall(CourseManager.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class CoursePurchasedEventResponse extends BaseEventResponse {
        public String user;

        public BigInteger courseId;

        public BigInteger price;
    }
}
