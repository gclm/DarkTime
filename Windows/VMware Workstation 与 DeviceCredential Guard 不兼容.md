## VMware Workstation 与 DeviceCredential Guard 不兼容

1. 在设置面板找到内核隔离选择关闭（很重要!!!）  
2. 关闭的Hyper-V（默认关闭，未开启就不用管）  
3. 禁用凭证  

“win+ R“打开运行，输入gpedit.msc输入，确定打开本地组策略编辑器  
转到本地计算机策略 > 计算机配置 > 管理模板>系统 > Device Guard  
打开 基于虚拟化的安全设置为“已禁用”  
按 “win键+ X”，选择“Windows PowerShell（管理员）（A）”  
输入命令：  
```shell
bcdedit /set hypervisorlaunchtype off
```