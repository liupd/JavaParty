# 重构之道

***重构（Refactoring）***就是通过调整程序代码改善软件的质量、性能，使其程序的设计模式和架构更趋合理，提高软件的扩展性和维护性。

如果把代码的发展比作人类的进化，那么重构行为就是代码的进化过程。

![进化](https://raw.githubusercontent.com/atlantis1024/JavaParty/master/images/%E7%BC%96%E7%A8%8B/%E9%AB%98%E6%95%88%E7%BC%96%E7%A8%8B/%E9%87%8D%E6%9E%84/refactoring.jpg)



## 第一篇 代码的坏味道

### [Bloaters](https://sourcemaking.com/refactoring/smells/bloaters)

​    ![img](https://sourcemaking.com/images/refactoring-illustrations/bloaters.png)

> ***这种坏味道意味着：代码，方法和类，逐渐增加到难以工作的规模。通常，这种坏味道不会立即暴露，而是随着程序的演变而积累（特别是当没有人努力去根除它们时）。***

- [过长函数(Long Method)](https://sourcemaking.com/refactoring/smells/long-method)

- [过大的类(Large Class)](https://sourcemaking.com/refactoring/smells/large-class)

- [基本类型偏执(Primitive Obsession)](https://sourcemaking.com/refactoring/smells/primitive-obsession)

- [过长参数列表(Long Parameter List)](https://sourcemaking.com/refactoring/smells/long-parameter-list)

- [数据泥团(Data Clumps)](https://sourcemaking.com/refactoring/smells/data-clumps)




### 滥用面向对象(Object-Orientation Abusers)

​    ![img](https://sourcemaking.com/images/refactoring-illustrations/oo-abusers.png)  

> ***这种坏味道意味着：代码部分或完全地违背了面向对象编程原则。***

- [Switch 声明(Switch Statements)](https://sourcemaking.com/refactoring/smells/switch-statements)
- [临时字段(Temporary Field)](https://sourcemaking.com/refactoring/smells/temporary-field)
- [被拒绝的遗赠(Refused Bequest)](https://sourcemaking.com/refactoring/smells/refused-bequest)
- [异曲同工的类(Alternative Classes with Different Interfaces)](https://sourcemaking.com/refactoring/smells/alternative-classes-with-different-interfaces)




### 变革的障碍(Change Preventers)

​    ![img](https://sourcemaking.com/images/refactoring-illustrations/change-preventers.png)  

> ***这种坏味道意味着：当你需要改变一处代码时，却发现不得不改变其他的地方。这使得程序开发变得复杂、代价高昂。***

- [发散式变化(Divergent Change)](https://sourcemaking.com/refactoring/smells/divergent-change)
- [霰弹式修改(Shotgun Surgery)](https://sourcemaking.com/refactoring/smells/shotgun-surgery)
- [平行继承体系(Parallel Inheritance Hierarchies)](https://sourcemaking.com/refactoring/smells/parallel-inheritance-hierarchies)




### 非必要的(Dispensables)

​    ![img](https://sourcemaking.com/images/refactoring-illustrations/dispensables.png)  

>***这种坏味道意味着：这样的代码可有可无，它的存在反而影响整体代码的整洁和可读性。***
>

- [过多的注释(Comments)](https://sourcemaking.com/refactoring/smells/comments)
- [重复代码(Duplicate Code)](https://sourcemaking.com/refactoring/smells/duplicate-code)
- [冗余类(Lazy Class)](https://sourcemaking.com/refactoring/smells/lazy-class)
- [纯稚的数据类(Data Class)](https://sourcemaking.com/refactoring/smells/data-class)
- [死代码(Dead Code)](https://sourcemaking.com/refactoring/smells/dead-code)
- [夸夸其谈未来性(Speculative Generality)](https://sourcemaking.com/refactoring/smells/speculative-generality)




### 耦合(Couplers)

​    ![img](https://sourcemaking.com/images/refactoring-illustrations/couplers.png)  

> ***这种坏味道意味着：不同类之间过度耦合。***

- [依恋情结(Feature Envy)](https://sourcemaking.com/refactoring/smells/feature-envy)
- [狎昵关系(Inappropriate Intimacy)](https://sourcemaking.com/refactoring/smells/inappropriate-intimacy)
- [过度耦合的消息链(Message Chains)](https://sourcemaking.com/refactoring/smells/message-chains)
- [中间人(Middle Man)](https://sourcemaking.com/refactoring/smells/middle-man)
- [不完美的库类(Incomplete Library Class)](https://sourcemaking.com/refactoring/smells/incomplete-library-class)