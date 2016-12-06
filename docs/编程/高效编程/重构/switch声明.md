# Switch Statements

### Signs and Symptoms

You have a complex `switch` operator or sequence of `if` statements.

![img](https://sourcemaking.com/images/refactoring-illustrations/switch-statements-1.png)

### Reasons for the Problem

Relatively rare use of `switch` and `case` operators is one of the hallmarks of object-oriented code. Often code for a single `switch` can be scattered in different places in the program. When a new condition is added, you have to find all the `switch` code and modify it.

As a rule of thumb, when you see `switch` you should think of polymorphism.

### Treatment

-  To isolate `switch` and put it in the right class, you may need [Extract Method](https://sourcemaking.com/refactoring/extract-method) and then [Move Method](https://sourcemaking.com/refactoring/move-method).
-  If a `switch` is based on type code, such as when the program's runtime mode is switched, use [Replace Type Code with Subclasses](https://sourcemaking.com/refactoring/replace-type-code-with-subclasses) or [Replace Type Code with State/Strategy](https://sourcemaking.com/refactoring/replace-type-code-with-state-strategy).
- After specifying the inheritance structure, use [Replace Conditional with Polymorphism](https://sourcemaking.com/refactoring/replace-conditional-with-polymorphism).
-  If there are not too many conditions in the operator and they all call same method with different parameters, polymorphism will be superfluous. If this case, you can break that method into multiple smaller methods with [Replace Parameter with Explicit Methods](https://sourcemaking.com/refactoring/replace-parameter-with-explicit-methods) and change the `switch` accordingly.
- If one of the conditional options is `null`, use [Introduce Null Object](https://sourcemaking.com/refactoring/introduce-null-object).

### Payoff

- Improved code organization.

![img](https://sourcemaking.com/images/refactoring-illustrations/switch-statements-2.png)

### When to Ignore

- When a `switch` operator performs simple actions, there is no reason to make code changes.
- Often `switch` operators are used by factory design patterns ([Factory Method](http://sourcemaking.com/design_patterns/factory_method) and [Abstract Factory](http://sourcemaking.com/design_patterns/abstract_factory)) to select a created class.