<map>
		<node ID="root" TEXT="管家婆系统">		<node TEXT="1. 绪论" ID="9316b03dc360f0f4" STYLE="bubble" POSITION="right">
		<node TEXT="1.1 开发背景及意义" ID="7d16b03dd7973019" STYLE="fork">
		</node>
		<node TEXT="1.2 研究内容" ID="3d016b03de349f112" STYLE="fork">
		<node TEXT="文件处理模块：包括excel文件导入功能、excel文件导出功能、数据库备份功能、数据库恢复功能及退出功能。" ID="3b316b03df65f4172" STYLE="fork">
		</node>
		<node TEXT="编辑模块：包括添加账目功能、删除账目功能及修改账目功能。" ID="35a16b03e1c5dd12b" STYLE="fork">
		</node>
		<node TEXT="查询模块：包括查询项目功能、条件查询项目功能、图表展示功能及报告功能" ID="c216b2fcdfc6307a" STYLE="fork">
		</node>
		<node TEXT="系统维护模块：包括添加分类功能、调整用户信息功能、更改主题功能及系统帮助功能。" ID="27516b03e63b710ca" STYLE="fork">
		</node>
		</node>
		<node TEXT="1.3 开发工具及相关技术" ID="7616b03e8a01a15c" STYLE="fork">
		<node TEXT="1.3.1 常用开发工具" ID="16816b2fb71b27085" STYLE="fork">
		<node TEXT="1. IntelliJ IDEA 2018.3.5" ID="25f16b2fafdef9094" STYLE="fork">
		</node>
		<node TEXT="2. JavaFX Scene Builder 2.0" ID="23a16b2fb1be0018d" STYLE="fork">
		</node>
		<node TEXT="3.  MySQL-Front" ID="3df16b2fb84e250fd" STYLE="fork">
		</node>
		</node>
		<node TEXT="1.3.2 运行环境" ID="20c16b2fb9a0b7063" STYLE="fork">
		<node TEXT="JDK 1.8.0" ID="15016b2fb9bd4101c" STYLE="fork">
		</node>
		<node TEXT="MySQL 5.5.15" ID="24616b2fbb972b014" STYLE="fork">
		</node>
		</node>
		</node>
		<node TEXT="1.4 需求分析" ID="2af16b2fbdeff6193" STYLE="fork">
		<node TEXT="1.4.1 可行性分析" ID="e516b2fbeb466046" STYLE="fork">
		<node TEXT="1. 系统的必要性" ID="116b2fbefc9b054" STYLE="fork">
		</node>
		<node TEXT="2. 系统的可行性" ID="2616b2fc87d6a017" STYLE="fork">
		</node>
		</node>
		<node TEXT="1.4.2 系统需求" ID="3316b2fcccb410f7" STYLE="fork">
		</node>
		<node TEXT="1.4.3 功能需求" ID="13c16b2fd8f51811b" STYLE="fork">
		<node TEXT="实现用户能够注册登录" ID="d516b2fd91f0e051" STYLE="fork">
		</node>
		<node TEXT="窗体界面要求美观大方、个性化、功能全面、操作简单。 " ID="d016b2fd99808146" STYLE="fork">
		</node>
		<node TEXT="要求实现excel文件导入数据及excel导出" ID="15316b2fd9a28e195" STYLE="fork">
		</node>
		<node TEXT="提供数据库备份及恢复的功能" ID="13c16b2fda23c801f" STYLE="fork">
		</node>
		<node TEXT="实现账目的添加、删除及修改功能" ID="a116b2fda66d814d" STYLE="fork">
		</node>
		<node TEXT="实现普通查询、条件查询、图表查询和生成报告的功能" ID="25216b2fdace8406e" STYLE="fork">
		</node>
		<node TEXT="提供用户自定义添加支出分类和收入分类" ID="14b16b2fdb723c11b" STYLE="fork">
		</node>
		<node TEXT="提供用户自定义图像及修改用户登录密码的功能" ID="3f16b2fdbdb5910b" STYLE="fork">
		</node>
		<node TEXT="实现系统皮肤主题的切换" ID="1c516b2fdc3955114" STYLE="fork">
		</node>
		<node TEXT="还有系统的帮助功能" ID="2f716b2fdca3fa095" STYLE="fork">
		</node>
		<node TEXT="具有易维护性和易操作性" ID="2e16b2fdcd99d09b" STYLE="fork">
		</node>
		</node>
		<node TEXT="1.4.4 性能要求" ID="b716b2fdd69f6104" STYLE="fork">
		<node TEXT="窗体界面要求美观大方、功能全面、操作简单。" ID="36216b2fdd81ce183" STYLE="fork">
		</node>
		<node TEXT="程序整体结构和操作流程合理顺畅,实现人性化设计。" ID="28716b2fdda5f513e1" STYLE="fork">
		</node>
		<node TEXT="规范、完善的基础信息设置。" ID="27f16b2fdda5f51242" STYLE="fork">
		</node>
		<node TEXT="提供账目的增删改的功能" ID="12b16b2fddef80023" STYLE="fork">
		</node>
		<node TEXT="实现查询及条件相关查询的功能" ID="1d916b2fde415b0f3" STYLE="fork">
		</node>
		<node TEXT="为操作员提供密码修改功能。" ID="2d516b2fddc941195" STYLE="fork">
		</node>
		<node TEXT="系统运行稳定、安全可靠。" ID="1d216b2fddcab104d1" STYLE="fork">
		</node>
		</node>
		</node>
		<node TEXT="1.5 系统数据库设计" ID="28816b2fdef15c068" STYLE="fork">
		<node TEXT="1.5.1 MySQL数据库概述" ID="1a816b2fdf1c2a135" STYLE="fork">
		</node>
		<node TEXT="1.5.2 系统实体E-R图" ID="ae16b2fe3321501" STYLE="fork">
		<node TEXT="1. 用户实体" ID="37f16b2ff595eb06" STYLE="fork">
		</node>
		<node TEXT="2. 记录实体" ID="20116b2ff6435e021" STYLE="fork">
		</node>
		</node>
		<node TEXT="1.5.3 系统数据表设计" ID="6816b300498590a2" STYLE="fork">
		<node TEXT="1. tb_users（用户表）" ID="10516b30207f1113" STYLE="fork">
		</node>
		<node TEXT="2. tb_records（记录表）" ID="36916b3020ad0004d" STYLE="fork">
		</node>
		</node>
		</node>
		<node TEXT="1.6 系统设计" ID="b816b3028082107f" STYLE="fork">
		<node TEXT="1.6.1 系统实现关系图" ID="25316b303d7ffd0e2" STYLE="fork">
		</node>
		<node TEXT="1.6.2 系统功能模块设计" ID="13116b303e086100b" STYLE="fork">
		<node TEXT="1. 系统的功能模块" ID="33216b303e2048041" STYLE="fork">
		</node>
		<node TEXT="2. 系统功能模块特点" ID="31f16b303e4f4d028" STYLE="fork">
		</node>
		</node>
		</node>
		<node TEXT="1.7 系统功能实现" ID="816b303eca6506e" STYLE="fork">
		<node TEXT="1.7.1 文件处理模块设计" ID="1416b303ee7ac167" STYLE="fork">
		<node TEXT="excel导入" ID="22d16b30408e17093" STYLE="fork">
		</node>
		<node TEXT="excel导出" ID="14d16b3042dc18024" STYLE="fork">
		</node>
		<node TEXT="备份" ID="3ab16b30437b110f3" STYLE="fork">
		</node>
		<node TEXT="恢复" ID="39816b3043f2f5131" STYLE="fork">
		</node>
		<node TEXT="退出" ID="f416b3043fac40b9" STYLE="fork">
		</node>
		</node>
		<node TEXT="1.7.2 编辑模块设计" ID="18c16b303f541c125" STYLE="fork">
		<node TEXT="添加界面窗体" ID="24216b3044fd08141" STYLE="fork">
		</node>
		<node TEXT="删除界面窗体" ID="1cd16b3045065a12f" STYLE="fork">
		</node>
		<node TEXT="修改界面窗体" ID="13416b30450c6e0cd" STYLE="fork">
		</node>
		</node>
		<node TEXT="1.7.3 查询模块设计" ID="24016b303f81df118" STYLE="fork">
		<node TEXT="查询界面窗体" ID="27116b30473187014" STYLE="fork">
		</node>
		<node TEXT="条件查询" ID="10b16b304738d5037" STYLE="fork">
		<node TEXT="按日期查询界面窗体" ID="2c416b3047de42073" STYLE="fork">
		</node>
		<node TEXT="按分类查询界面窗体" ID="16216b30483f9d116" STYLE="fork">
		</node>
		<node TEXT="按备注查询界面窗体" ID="10e16b304882c913c" STYLE="fork">
		</node>
		</node>
		<node TEXT="图表" ID="28416b30475690123" STYLE="fork">
		<node TEXT="条形图界面窗体" ID="33816b30492c9810e" STYLE="fork">
		</node>
		<node TEXT="折线图界面窗体" ID="26816b3049c217061" STYLE="fork">
		</node>
		<node TEXT="饼图界面窗体" ID="29216b304a38d5173" STYLE="fork">
		</node>
		</node>
		<node TEXT="报告界面窗体" ID="c716b30476df4177" STYLE="fork">
		</node>
		</node>
		<node TEXT="1.7.4 系统维护模块设计" ID="38c16b303fa389097" STYLE="fork">
		<node TEXT="添加分类界面窗体" ID="ed16b304d609712b" STYLE="fork">
		</node>
		<node TEXT="用户信息界面窗体" ID="21516b304d71a50fb" STYLE="fork">
		</node>
		<node TEXT="主题界面窗体（经典黑）" ID="3e616b304ebe5917" STYLE="fork">
		</node>
		<node TEXT="帮助窗体界面" ID="1c516b304fd55a132" STYLE="fork">
		</node>
		</node>
		</node>
		<node TEXT="1.8 不足" ID="37d16b306a77c001f" STYLE="fork">
		</node>
		<node TEXT="1.9 参考文献" ID="c216b306a9f82014" STYLE="fork">
		</node>
		</node>
</node>
</map>