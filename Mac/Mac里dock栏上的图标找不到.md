# Mac里dock栏上的图标找不到

最近是不是用Visual Studio Code有些过猛，打开VS Code很正常，但是在Dock上居然找不到图标，找不到原因。
可以这样解决，从Application文件夹中打开terminal命令行工具，输入：

```
killall Dock
```
回车，这样Dock就可以重启，图标也正常了