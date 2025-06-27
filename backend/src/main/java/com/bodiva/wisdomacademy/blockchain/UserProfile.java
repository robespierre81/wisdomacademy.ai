package com.bodiva.wisdomacademy.blockchain;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
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
import org.web3j.tuples.generated.Tuple8;
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
public class UserProfile extends Contract {
    public static final String BINARY = "6080604052348015600e575f5ffd5b50610e0a8061001c5f395ff3fe608060405234801561000f575f5ffd5b5060043610610090575f3560e01c8063987ee15611610063578063987ee156146100f8578063c2f7be8a1461011f578063d0a63e7714610132578063d637dcfa14610145578063d7d53deb14610158575f5ffd5b80631b77648f146100945780634a0a1bf8146100bd57806360903be6146100d25780637bdecea1146100e5575b5f5ffd5b6100a76100a2366004610981565b61016b565b6040516100b491906109db565b60405180910390f35b6100d06100cb3660046109ed565b6101d5565b005b6100d06100e0366004610ab4565b61023f565b6100d06100f3366004610b42565b6102f9565b61010b610106366004610981565b610371565b6040516100b4989796959493929190610b87565b6100d061012d366004610b42565b6106f7565b6100d0610140366004610b42565b61077a565b6100d0610153366004610ab4565b6107fd565b6100d0610166366004610b42565b6108e3565b6001600160a01b0381165f90815260208181526040918290206008018054835181840281018401909452808452606093928301828280156101c957602002820191905f5260205f20905b8154815260200190600101908083116101b5575b50505050509050919050565b6001600160a01b0382165f8181526020818152604080832060080180546001810182559084529190922001839055517fe286cd8324fe410f09a3449991d62b3200c09244dfd683075cfcb78035e748de906102339084815260200190565b60405180910390a25050565b335f9081526001602052604090205460ff166102765760405162461bcd60e51b815260040161026d90610c26565b60405180910390fd5b335f9081526020819052604090208061028f8582610cd7565b506001810161029e8482610cd7565b50600281016102ad8382610cd7565b50336001600160a01b03167f6420daf1b58438e85465ef21dee378d307a509caab491149e3f066c5caf1266d8585856040516102eb93929190610d92565b60405180910390a250505050565b335f9081526001602052604090205460ff166103275760405162461bcd60e51b815260040161026d90610c26565b335f818152602081815260409182902060030184905590518381527fa4f4df726d60e3886846ea1ee4281c6404b9090c5520f72046c34ccbcb20c60e91015b60405180910390a250565b6001600160a01b0381165f908152600160205260408120546060918291829190829081908190819060ff166103b85760405162461bcd60e51b815260040161026d90610c26565b5f5f5f8b6001600160a01b03166001600160a01b031681526020019081526020015f209050805f01816001018260020183600301548460040185600501866006018760070187805461040990610c53565b80601f016020809104026020016040519081016040528092919081815260200182805461043590610c53565b80156104805780601f1061045757610100808354040283529160200191610480565b820191905f5260205f20905b81548152906001019060200180831161046357829003601f168201915b5050505050975086805461049390610c53565b80601f01602080910402602001604051908101604052809291908181526020018280546104bf90610c53565b801561050a5780601f106104e15761010080835404028352916020019161050a565b820191905f5260205f20905b8154815290600101906020018083116104ed57829003601f168201915b5050505050965085805461051d90610c53565b80601f016020809104026020016040519081016040528092919081815260200182805461054990610c53565b80156105945780601f1061056b57610100808354040283529160200191610594565b820191905f5260205f20905b81548152906001019060200180831161057757829003601f168201915b50505050509550838054806020026020016040519081016040528092919081815260200182805480156105e457602002820191905f5260205f20905b8154815260200190600101908083116105d0575b505050505093508280548060200260200160405190810160405280929190818152602001828054801561063457602002820191905f5260205f20905b815481526020019060010190808311610620575b505050505092508180548060200260200160405190810160405280929190818152602001828054801561068457602002820191905f5260205f20905b815481526020019060010190808311610670575b50505050509150808054806020026020016040519081016040528092919081815260200182805480156106d457602002820191905f5260205f20905b8154815260200190600101908083116106c0575b505050505090509850985098509850985098509850985050919395975091939597565b335f9081526001602052604090205460ff166107255760405162461bcd60e51b815260040161026d90610c26565b335f8181526020818152604080832060070180546001810182559084529190922001839055517f23b76f83bbb67f70abe29d17f1bcf82526b32fde6dd97e1d20726afcd376d78f906103669084815260200190565b335f9081526001602052604090205460ff166107a85760405162461bcd60e51b815260040161026d90610c26565b335f8181526020818152604080832060050180546001810182559084529190922001839055517f7872d6dcfaa520ffdce472ccd905f26ac75c353de0e61b44ec9158d48a053c12906103669084815260200190565b335f9081526001602052604090205460ff161561085c5760405162461bcd60e51b815260206004820152601760248201527f5573657220616c72656164792072656769737465726564000000000000000000604482015260640161026d565b335f908152602081905260409020806108758582610cd7565b50600181016108848482610cd7565b50600281016108938382610cd7565b50335f81815260016020819052604091829020805460ff19169091179055517f6420daf1b58438e85465ef21dee378d307a509caab491149e3f066c5caf1266d906102eb90879087908790610d92565b335f9081526001602052604090205460ff166109115760405162461bcd60e51b815260040161026d90610c26565b335f8181526020818152604080832060060180546001810182559084529190922001839055517fdb9659cf99e85a2ab0ecaa71a65db451478c90f6e3f72971f7eb6bdff5341558906103669084815260200190565b80356001600160a01b038116811461097c575f5ffd5b919050565b5f60208284031215610991575f5ffd5b61099a82610966565b9392505050565b5f8151808452602084019350602083015f5b828110156109d15781518652602095860195909101906001016109b3565b5093949350505050565b602081525f61099a60208301846109a1565b5f5f604083850312156109fe575f5ffd5b610a0783610966565b946020939093013593505050565b634e487b7160e01b5f52604160045260245ffd5b5f82601f830112610a38575f5ffd5b813567ffffffffffffffff811115610a5257610a52610a15565b604051601f8201601f19908116603f0116810167ffffffffffffffff81118282101715610a8157610a81610a15565b604052818152838201602001851015610a98575f5ffd5b816020850160208301375f918101602001919091529392505050565b5f5f5f60608486031215610ac6575f5ffd5b833567ffffffffffffffff811115610adc575f5ffd5b610ae886828701610a29565b935050602084013567ffffffffffffffff811115610b04575f5ffd5b610b1086828701610a29565b925050604084013567ffffffffffffffff811115610b2c575f5ffd5b610b3886828701610a29565b9150509250925092565b5f60208284031215610b52575f5ffd5b5035919050565b5f81518084528060208401602086015e5f602082860101526020601f19601f83011685010191505092915050565b61010081525f610b9b61010083018b610b59565b8281036020840152610bad818b610b59565b90508281036040840152610bc1818a610b59565b90508760608401528281036080840152610bdb81886109a1565b905082810360a0840152610bef81876109a1565b905082810360c0840152610c0381866109a1565b905082810360e0840152610c1781856109a1565b9b9a5050505050505050505050565b602080825260139082015272155cd95c881b9bdd081c9959da5cdd195c9959606a1b604082015260600190565b600181811c90821680610c6757607f821691505b602082108103610c8557634e487b7160e01b5f52602260045260245ffd5b50919050565b601f821115610cd257805f5260205f20601f840160051c81016020851015610cb05750805b601f840160051c820191505b81811015610ccf575f8155600101610cbc565b50505b505050565b815167ffffffffffffffff811115610cf157610cf1610a15565b610d0581610cff8454610c53565b84610c8b565b6020601f821160018114610d37575f8315610d205750848201515b5f19600385901b1c1916600184901b178455610ccf565b5f84815260208120601f198516915b82811015610d665787850151825560209485019460019092019101610d46565b5084821015610d8357868401515f19600387901b60f8161c191681555b50505050600190811b01905550565b606081525f610da46060830186610b59565b8281036020840152610db68186610b59565b90508281036040840152610dca8185610b59565b969550505050505056fea2646970667358221220ea3b48a2e6a5130c00af1cd79ec8350a894f745d70e70a9dcbfbe3b57b3eff1b64736f6c634300081d0033";

    public static final String FUNC_ADDBADGE = "addBadge";

    public static final String FUNC_ADDCOURSE = "addCourse";

    public static final String FUNC_ADDLEARNINGPLAN = "addLearningPlan";

    public static final String FUNC_ADDMENTORSESSION = "addMentorSession";

    public static final String FUNC_GETCOURSES = "getCourses";

    public static final String FUNC_GETUSERPROFILE = "getUserProfile";

    public static final String FUNC_REGISTERUSER = "registerUser";

    public static final String FUNC_UPDATETOKENS = "updateTokens";

    public static final String FUNC_UPDATEUSER = "updateUser";

    public static final Event BADGEADDED_EVENT = new Event("BadgeAdded", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event COURSEADDED_EVENT = new Event("CourseAdded", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event LEARNINGPLANADDED_EVENT = new Event("LearningPlanAdded", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event MENTORSESSIONADDED_EVENT = new Event("MentorSessionAdded", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event PROFILEUPDATED_EVENT = new Event("ProfileUpdated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event TOKENSUPDATED_EVENT = new Event("TokensUpdated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected UserProfile(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected UserProfile(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected UserProfile(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected UserProfile(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<BadgeAddedEventResponse> getBadgeAddedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(BADGEADDED_EVENT, transactionReceipt);
        ArrayList<BadgeAddedEventResponse> responses = new ArrayList<BadgeAddedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            BadgeAddedEventResponse typedResponse = new BadgeAddedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.user = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.badgeId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static BadgeAddedEventResponse getBadgeAddedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(BADGEADDED_EVENT, log);
        BadgeAddedEventResponse typedResponse = new BadgeAddedEventResponse();
        typedResponse.log = log;
        typedResponse.user = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.badgeId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<BadgeAddedEventResponse> badgeAddedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getBadgeAddedEventFromLog(log));
    }

    public Flowable<BadgeAddedEventResponse> badgeAddedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(BADGEADDED_EVENT));
        return badgeAddedEventFlowable(filter);
    }

    public static List<CourseAddedEventResponse> getCourseAddedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(COURSEADDED_EVENT, transactionReceipt);
        ArrayList<CourseAddedEventResponse> responses = new ArrayList<CourseAddedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            CourseAddedEventResponse typedResponse = new CourseAddedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.user = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.courseId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static CourseAddedEventResponse getCourseAddedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(COURSEADDED_EVENT, log);
        CourseAddedEventResponse typedResponse = new CourseAddedEventResponse();
        typedResponse.log = log;
        typedResponse.user = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.courseId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<CourseAddedEventResponse> courseAddedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getCourseAddedEventFromLog(log));
    }

    public Flowable<CourseAddedEventResponse> courseAddedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(COURSEADDED_EVENT));
        return courseAddedEventFlowable(filter);
    }

    public static List<LearningPlanAddedEventResponse> getLearningPlanAddedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(LEARNINGPLANADDED_EVENT, transactionReceipt);
        ArrayList<LearningPlanAddedEventResponse> responses = new ArrayList<LearningPlanAddedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            LearningPlanAddedEventResponse typedResponse = new LearningPlanAddedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.user = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.learningPlanId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static LearningPlanAddedEventResponse getLearningPlanAddedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(LEARNINGPLANADDED_EVENT, log);
        LearningPlanAddedEventResponse typedResponse = new LearningPlanAddedEventResponse();
        typedResponse.log = log;
        typedResponse.user = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.learningPlanId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<LearningPlanAddedEventResponse> learningPlanAddedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getLearningPlanAddedEventFromLog(log));
    }

    public Flowable<LearningPlanAddedEventResponse> learningPlanAddedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(LEARNINGPLANADDED_EVENT));
        return learningPlanAddedEventFlowable(filter);
    }

    public static List<MentorSessionAddedEventResponse> getMentorSessionAddedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(MENTORSESSIONADDED_EVENT, transactionReceipt);
        ArrayList<MentorSessionAddedEventResponse> responses = new ArrayList<MentorSessionAddedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            MentorSessionAddedEventResponse typedResponse = new MentorSessionAddedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.user = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.mentorSessionId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static MentorSessionAddedEventResponse getMentorSessionAddedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(MENTORSESSIONADDED_EVENT, log);
        MentorSessionAddedEventResponse typedResponse = new MentorSessionAddedEventResponse();
        typedResponse.log = log;
        typedResponse.user = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.mentorSessionId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<MentorSessionAddedEventResponse> mentorSessionAddedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getMentorSessionAddedEventFromLog(log));
    }

    public Flowable<MentorSessionAddedEventResponse> mentorSessionAddedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(MENTORSESSIONADDED_EVENT));
        return mentorSessionAddedEventFlowable(filter);
    }

    public static List<ProfileUpdatedEventResponse> getProfileUpdatedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(PROFILEUPDATED_EVENT, transactionReceipt);
        ArrayList<ProfileUpdatedEventResponse> responses = new ArrayList<ProfileUpdatedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ProfileUpdatedEventResponse typedResponse = new ProfileUpdatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.user = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.name = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.bio = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.profilePhoto = (String) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static ProfileUpdatedEventResponse getProfileUpdatedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(PROFILEUPDATED_EVENT, log);
        ProfileUpdatedEventResponse typedResponse = new ProfileUpdatedEventResponse();
        typedResponse.log = log;
        typedResponse.user = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.name = (String) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.bio = (String) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.profilePhoto = (String) eventValues.getNonIndexedValues().get(2).getValue();
        return typedResponse;
    }

    public Flowable<ProfileUpdatedEventResponse> profileUpdatedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getProfileUpdatedEventFromLog(log));
    }

    public Flowable<ProfileUpdatedEventResponse> profileUpdatedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PROFILEUPDATED_EVENT));
        return profileUpdatedEventFlowable(filter);
    }

    public static List<TokensUpdatedEventResponse> getTokensUpdatedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(TOKENSUPDATED_EVENT, transactionReceipt);
        ArrayList<TokensUpdatedEventResponse> responses = new ArrayList<TokensUpdatedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TokensUpdatedEventResponse typedResponse = new TokensUpdatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.user = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static TokensUpdatedEventResponse getTokensUpdatedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(TOKENSUPDATED_EVENT, log);
        TokensUpdatedEventResponse typedResponse = new TokensUpdatedEventResponse();
        typedResponse.log = log;
        typedResponse.user = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<TokensUpdatedEventResponse> tokensUpdatedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getTokensUpdatedEventFromLog(log));
    }

    public Flowable<TokensUpdatedEventResponse> tokensUpdatedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TOKENSUPDATED_EVENT));
        return tokensUpdatedEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> addBadge(BigInteger badgeId) {
        final Function function = new Function(
                FUNC_ADDBADGE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(badgeId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> addCourse(String user, BigInteger courseId) {
        final Function function = new Function(
                FUNC_ADDCOURSE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, user), 
                new org.web3j.abi.datatypes.generated.Uint256(courseId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> addLearningPlan(BigInteger planId) {
        final Function function = new Function(
                FUNC_ADDLEARNINGPLAN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(planId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> addMentorSession(BigInteger sessionId) {
        final Function function = new Function(
                FUNC_ADDMENTORSESSION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(sessionId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<List> getCourses(String user) {
        final Function function = new Function(FUNC_GETCOURSES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, user)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Uint256>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteFunctionCall<Tuple8<String, String, String, BigInteger, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>>> getUserProfile(String user) {
        final Function function = new Function(FUNC_GETUSERPROFILE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, user)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<Uint256>>() {}));
        return new RemoteFunctionCall<Tuple8<String, String, String, BigInteger, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>>>(function,
                new Callable<Tuple8<String, String, String, BigInteger, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>>>() {
                    @Override
                    public Tuple8<String, String, String, BigInteger, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple8<String, String, String, BigInteger, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue(), 
                                convertToNative((List<Uint256>) results.get(4).getValue()), 
                                convertToNative((List<Uint256>) results.get(5).getValue()), 
                                convertToNative((List<Uint256>) results.get(6).getValue()), 
                                convertToNative((List<Uint256>) results.get(7).getValue()));
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> registerUser(String name, String bio, String profilePhoto) {
        final Function function = new Function(
                FUNC_REGISTERUSER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(name), 
                new org.web3j.abi.datatypes.Utf8String(bio), 
                new org.web3j.abi.datatypes.Utf8String(profilePhoto)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> updateTokens(BigInteger amount) {
        final Function function = new Function(
                FUNC_UPDATETOKENS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> updateUser(String name, String bio, String profilePhoto) {
        final Function function = new Function(
                FUNC_UPDATEUSER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(name), 
                new org.web3j.abi.datatypes.Utf8String(bio), 
                new org.web3j.abi.datatypes.Utf8String(profilePhoto)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static UserProfile load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new UserProfile(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static UserProfile load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new UserProfile(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static UserProfile load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new UserProfile(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static UserProfile load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new UserProfile(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<UserProfile> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(UserProfile.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<UserProfile> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(UserProfile.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<UserProfile> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(UserProfile.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<UserProfile> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(UserProfile.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class BadgeAddedEventResponse extends BaseEventResponse {
        public String user;

        public BigInteger badgeId;
    }

    public static class CourseAddedEventResponse extends BaseEventResponse {
        public String user;

        public BigInteger courseId;
    }

    public static class LearningPlanAddedEventResponse extends BaseEventResponse {
        public String user;

        public BigInteger learningPlanId;
    }

    public static class MentorSessionAddedEventResponse extends BaseEventResponse {
        public String user;

        public BigInteger mentorSessionId;
    }

    public static class ProfileUpdatedEventResponse extends BaseEventResponse {
        public String user;

        public String name;

        public String bio;

        public String profilePhoto;
    }

    public static class TokensUpdatedEventResponse extends BaseEventResponse {
        public String user;

        public BigInteger amount;
    }
}
