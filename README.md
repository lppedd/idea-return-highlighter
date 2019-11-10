<img align="left" width="80" height="78" src="https://raw.githubusercontent.com/lppedd/idea-return-highlighter/master/images/return-highlighter-logo.png" alt="Plugin logo">

# Return Highlighter ([@JetBrains Plugins Repository][1])

<small>Supported IDE versions: `192.*` to `193.*`</small> 

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

 - Show a gutter icon next to the `return`ing lines
 - Highlight the `return` keyword, so that it stands out more

#### Supported languages

 - Java
 - JavaScript &amp; TypeScript
 - Need another language? [Open an issue][2]
  
#### Example

<img width="845" height="200" src="https://raw.githubusercontent.com/lppedd/idea-return-highlighter/master/images/usage-example.png" alt="Usage example">

The gutter icon is clickable, and will take you to the return keyword.
  
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

### Limit the scope of the plugin

Certain languages offer features like _lambda expressions_ (Java) or _function expressions_ (JavaScript).  
This means potentially you can have nested functions, and thus nested `return` statements.

To highlight only top-level `return` keywords, depending on the language, look under  
**Settings > Return Highlighter > *[language]***  
And check **Only top-level return keywords**.

<img width="845" height="185" src="https://raw.githubusercontent.com/lppedd/idea-return-highlighter/master/images/scope.png" alt="Scope">

[1]: https://plugins.jetbrains.com/plugin/13303-return-highlighter
[2]: https://github.com/lppedd/idea-return-highlighter/issues/new?assignees=lppedd&labels=enhancement%2C+language&template=language-support.md&title=Language+support%3A+%5BLANGUAGE%5D
