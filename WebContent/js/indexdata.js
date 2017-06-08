var indexdata = 
[
    { text: '内部公文管理',isExpand: true, children: [
		{ url:"sendDoc",text:"发文登记"},
		{ url:"myInnerDoc",text:"我的发文"},
		{ url:"myInnerApprove",text:"公文审批"},
		{ url:"myInnerProcess",text:"收文办理"},
		{ url:"innerDoc_select",text:"公文查询"}
	]
    },
    { text: '外部公文管理', isExpand: true, children: [
		{ url:"sendOutDoc",text:"收文登记"},
		{ url:"myInnerDoc",text:"我的发文"},
		{ url:"myInnerDoc",text:"公文审批"},
		{ url:"myInnerDoc",text:"收文办理"}
	]
    }, 
	{ text: '账户管理', isExpand: true, children: [
	 { url: "changePass", text: "修改密码" },
	 { url: "chose_approver", text: "配置审核路径" }
	]}
];


var indexdata2 =
[
    { isexpand: "true", text: "表格", children: [
        { isexpand: "true", text: "可排序", children: [
		    { url: "dotnetdemos/grid/sortable/client.aspx", text: "客户端" },
            { url: "dotnetdemos/grid/sortable/server.aspx", text: "服务器" }
	    ]
        },
        { isexpand: "true", text: "可分页", children: [
		    { url: "dotnetdemos/grid/pager/client.aspx", text: "客户端" },
            { url: "dotnetdemos/grid/pager/server.aspx", text: "服务器" }
	    ]
        },
        { isexpand: "true", text: "树表格", children: [
		    { url: "dotnetdemos/grid/treegrid/tree.aspx", text: "树表格" }, 
		    { url: "dotnetdemos/grid/treegrid/tree2.aspx", text: "树表格2" }
	    ]
        }
    ]
    }
];
