package com.kavito.pattern.strategy;

import com.kavito.pattern.strategy.UserStrategy.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StrategyFactory {

    private Map<String, Strategy> map;

    public StrategyFactory() {

        List<Strategy> strategies = new ArrayList<>();
        strategies.add(new BronzeStrategy());
        strategies.add(new SilverStrategy());
        strategies.add(new GoldStrategy());
        strategies.add(new PlatinumStrategy());
        strategies.add(new DiamondStrategy());
        strategies.add(new CrownStrategy());
        strategies.add(new TrumpStrategy());
        strategies.add(new GodStrategy());

        // 添加到map中
        map = strategies.stream().collect(Collectors.toMap(Strategy::getLevel, strategy -> strategy));
    }
    // 静态内部类获取工程实例
    public static class Holder {
        public static StrategyFactory instance = new StrategyFactory();
    }

    public static StrategyFactory getInstance() {
        return Holder.instance;
    }

    public Strategy get(String level) {
        return map.get(level);
    }
}


/*
后续优化：
1、UserPayServiceStrategyFactory中提供了的register方法，他就是用来注册策略服务的。
接下来，我们就想办法调用register方法，把Spring通过IOC创建出来的Bean注册进去就行了。

2、可以借用Spring种提供的InitializingBean接口，这个接口为Bean提供了属性初始化后的处理方法，
它只包括afterPropertiesSet方法，凡是继承该接口的类，在bean的属性初始化后都会执行该方法。
只需要每一个策略服务的实现类都实现InitializingBean接口，并实现其afterPropertiesSet方法，
在这个方法中调用UserPayServiceStrategyFactory.register即可。
这样，在Spring初始化的时候，当创建每一种策略实现类的时候，
会在Bean的属性初始化之后，把这个Bean注册到UserPayServiceStrategyFactory中。
以上代码，其实还是有一些重复代码的，这里面还可以引入模板方法模式进一步精简，这里就不展开了。

策略工厂
public class StrategyFactory {

    private static Map<String,Strategy> map = new ConcurrentHashMap<String,Strategy>();

    public  static Strategy getByUserType(String type){
        return map.get(type);
    }

    public static void register(String userType,Strategy strategy){
        Assert.notNull(userType,"userType can't be null");
        map.put(userType,strategy);
    }
}

策略类
@Service
public class GodStrategy implements Strategy,InitializingBean {

    @Override
    public BigDecimal quote(BigDecimal orderPrice) {
         if (消费金额大于30元) {
            return 7折价格;
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        UserPayServiceStrategyFactory.register("ParticularlyVip",this);
    }
}

@Service
public class GodStrategy implements Strategy,InitializingBean {
    @Override
    public String reward() {
        return "无敌战神选手，奖励【黄金玛萨拉蒂】一辆";
    }

    @Override
    public String getLevel() {
        return UserLevelEnum.GOD.getCode();
    }

     @Override
    public void afterPropertiesSet() throws Exception {
        StrategyFactory.register("ParticularlyVip",this);
    }
}

*/


