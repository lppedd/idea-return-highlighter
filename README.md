:bell: <strong>Localization needed! PRs are welcomed</strong> (look [here][4] for bundles) :sunglasses:  

<img align="left" width="70" height="68" src="https://raw.githubusercontent.com/lppedd/idea-return-highlighter/master/images/return-highlighter-logo.png" alt="Plugin logo">

# Return Highlighter

### Available @ [JetBrains Plugins Repository][1]

Looking for the latest **plugin binaries**? Get them [here][3] as `.zip`  
<small>Supported IDE versions: `201.6668` to `*`</small> 

#### Supported languages

 - Java
 - JavaScript and TypeScript
 - Python
 - PHP
 - Need another language? [Open an issue][2]

-----

The aim of this plugin is to highlight `return` keywords in source code.  
Sometimes it happens we find difficult to read source files; take for example this piece of code

```typescript
1  private toggleRowCheck(rowId: string, doCheck: boolean): boolean {
2    if (doCheck) {
3      if (this.checkedRows.size >= (this.selectionOpt.limit || Infinity)) return false;
4      this.checkedRows.add(rowId);
5    } else {
6      this.checkedRows.delete(rowId);
7    }
8    return true;
9  }
```

It is not immediately apparent a `return` statement is present at line `3`.  
This is why the plugin does

 - Show a gutter icon next to the `return`ing lines (clicking on it will take you to the return statement!)
 - Highlight the `return` keyword, so that it stands out more
  
#### Example

<img width="845" height="200" src="https://raw.githubusercontent.com/lppedd/idea-return-highlighter/master/images/usage-example.png" alt="Usage example">
  
-----

The plugin offers some degree of customization.

### Choose for which languages to show the gutter icon

You can customize which languages will have the gutter icon via  
**Settings > Editor > General > Gutter Icons**

You'll find a _Return Highlighter_ section, with all available languages listed.

<img width="845" height="169" src="https://raw.githubusercontent.com/lppedd/idea-return-highlighter/master/images/gutter-icons.png" alt="Gutter icons">

### Disable/Customize the return keyword color highlighting

You can disable or customize how the return keyword is highlighted, to stand out more, via  
**Settings > Editor > Color Scheme > Return Highlighter**

To reset values to the default ones, check **Inherit values from**.

<img width="845" height="516" src="https://raw.githubusercontent.com/lppedd/idea-return-highlighter/master/images/style.png" alt="Style">

### Limit the scope of the plugin

- #### Top-level returns only
  Certain languages offer features like _lambda expressions_ (Java) or _function expressions_ (JavaScript).
  This means potentially you can have nested functions, and thus nested `return` statements.

  To highlight only top-level `return` keywords, depending on the language, look under  
  **Settings > Return Highlighter > *[language]***  
  and check **Only top-level return keywords**.

- #### Skip simple getters
  Highlighting `return` statements in simple functions/methods can be annoying.  
  Thus it is possible to disable it for such elements.

  To skip `return` keywords inside simple getters, look under  
  **Settings > Return Highlighter > *[language]***  
  and check **Skip simple getters**.
  
  <img width="845" height="177" src="https://raw.githubusercontent.com/lppedd/idea-return-highlighter/master/images/scope.png" alt="Scope">

  A _tooltip_ is there to show examples of simple getters, per language, but for the sake of README completeness
  here is one too in TypeScript
  
  ```
  isEnabled(): boolean {
    return true;
  }
  ```

-----

## Author

 - Edoardo Luppi (<lp.edoardo@gmail.com>)

[1]: https://plugins.jetbrains.com/plugin/13303-return-highlighter
[2]: https://github.com/lppedd/idea-return-highlighter/issues/new?assignees=lppedd&labels=enhancement%2C+language&template=language-support.md&title=Language+support%3A+%5BLANGUAGE%5D
[3]: https://github.com/lppedd/idea-return-highlighter/releases
[4]: https://github.com/lppedd/idea-return-highlighter/tree/master/src/main/resources/messages
