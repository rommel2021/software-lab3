package poll;

import java.util.*;

import auxiliary.Voter;
import pattern.SelectionStrategy;
import pattern.StatisticsStrategy;
import vote.VoteItem;
import vote.VoteType;
import vote.Vote;

public class GeneralPollImpl<C> implements Poll<C> {

    // 投票活动的名称
    String name;
    // 投票活动发起的时间
    Calendar date;
    // 候选对象集合
    List<C> candidates;
    // 投票人集合，key为投票人，value为其在本次投票中所占权重
    Map<Voter, Double> voters;
    // 拟选出的候选对象最大数量
    int quantity;
    // 本次投票拟采用的投票类型（合法选项及各自对应的分数）
    VoteType voteType;
    // 所有选票集合
    Set<Vote<C>> votes;
    // 计票结果，key为候选对象，value为其得分
    Map<C, Double> statistics;
    // 遴选结果，key为候选对象，value为其排序位次
    Map<C, Double> results;

    // Rep Invariants
    // name 不能为空
    // date 不能为空
    // quantity 大于0小于等于candidates元素数量
    // votes 数量等于参选人数量
    // statistics、statistics的元素个数等于候选者数量
    // results元素数量等于quantity
    // Abstract Function
    // 建立一个GeneralPollImpl类到投票问题的映射
    // Safety from Rep Exposure
    // name/date,quantity,votes,statistics等所有属性都为缺省，不会被其他包中的类直接访问

    public void checkRep() {
        assert name != null;
        assert date != null;
        assert quantity > 0;
        for (Vote<C> v : votes) {
            //一张选票里包含所有候选人
            for (VoteItem<C> vi : v.getVoteItems()) {
                assert candidates.contains( vi.getCandidate());
            }
            assert v.getVoteItems().size() == candidates.size();
        }
    }

    /**
     * 构造函数
     */
    public GeneralPollImpl() {

    }


    @Override
    public void setInfo(String name, Calendar date, VoteType type, int quantity) {
        this.name = name;
        this.date = date;
        this.voteType = type;
        this.quantity = quantity;
        votes = new HashSet<Vote<C>>();
        checkRep();
    }

    @Override
    public void addVoters(Map<Voter, Double> voters) {
        // TODO
        this.voters = voters;
        checkRep();
    }

    @Override
    public void addCandidates(List<C> candidates) {
        // TODO
        this.candidates = candidates;
        checkRep();
    }

    @Override
    public void addVote(Vote<C> vote) throws Exception {
        // 此处应首先检查该选票的合法性并标记，为此需扩展或修改rep
        // 一张选票要包含所有候选人 done
        // 不包含不在候选者中的人 done
        // 不允许出现的选项（支持不支持）done
        // 不能重复对一个人投票
        // 为此需要修改voteItem类的rep?

        HashSet<C> cs = new HashSet<>();
        for (VoteItem<C> voteItem : vote.getVoteItems()) {
            cs.add(voteItem.getCandidate());
            if(!candidates.contains((C)voteItem.getCandidate())){
                voteItem.setValid(false);
                vote.setIsLegal(false);
                return;
            }

//                throw new Exception("存在非候选者！！");
            if (!this.voteType.checkLegality(voteItem.getVoteValue())) {
                voteItem.setValid(false);
                vote.setIsLegal(false);
                return;
            }
        }
        if(cs.size()!=vote.getVoteItems().size()){
            vote.setIsLegal(false);
            return;
        }

//            throw new Exception("不能出现重复投票！");
        votes.add(vote);
        checkRep();
    }

    @Override
    public void statistics(StatisticsStrategy ss) {
        // 此处应首先检查当前所拥有的选票的合法性
        statistics=new HashMap<>();
        ss.totalScore(statistics,votes,voteType,voters);
        checkRep();
    }

    @Override
    public void selection(SelectionStrategy ss) {
        results=new HashMap<>();
        ss.select(statistics,results,quantity);
        checkRep();
    }

    @Override
    public String result() {
        String str="";
        for(C candidate:results.keySet()){
            str+=candidate.toString();
        }
        return str;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
        checkRep();
    }
    @Override
    public Set<Vote<C>> getLegalVotes() {
        HashSet<Vote<C>> cs = new HashSet<>();
        for(Vote<C> v:votes){
            if(v.getIsLegal() == true)
                cs.add(v);
        }
        return cs;
    }
    public Set<Vote<C>> getVotes() {
        return new HashSet<Vote<C>>(votes);
    }
}
