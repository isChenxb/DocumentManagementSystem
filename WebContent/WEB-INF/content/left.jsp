<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="lib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<link href="lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
<script src="lib/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="lib/ligerUI/js/plugins/ligerTree.js" type="text/javascript"></script>
<script src="lib/ligerUI/js/plugins/ligerMenu.js" type="text/javascript"></script>
<script type="text/javascript">
        var menu;
        var actionNodeID;
         function itemclick(item, i)
        {
            alert(actionNodeID + " | " + item.text);
        }
        $(function ()
        {
            menu = $.ligerMenu({ top: 100, left: 100, width: 120, items:
            [
            { text: '增加', click: itemclick, icon: 'add' },
            { text: '修改', click: itemclick },
            { line: true },
            { text: '查看', click: itemclick }
            ]
            });

            $("#tree1").ligerTree({ onContextmenu: function (node, e)
            { 
                actionNodeID = node.data.text;
                menu.show({ top: e.pageY, left: e.pageX });
                return false;
            }
            });
        });
    </script>

</head>
<body style="padding:10px">  
    <!--带复选框和Icon-->
    <div style="width:500px; height:500px; margin:10px; float:left; border:1px solid #ccc; overflow:auto;  ">
    <ul id="tree1">
        <li>
            <span>节点1</span>
            <ul>
                <li>
                    <span>节点1.1</span>
                     <ul>
                        <li><span>节点1.1.2</span></li>
                        <li><span>节点1.1.2</span></li>
                     </ul>
                 </li>
                 <li><span>节点1.2</span></li>
            </ul>
        </li> 
        <li><span>节点2</span></li>
        <li>
            <span>节点3</span>
            <ul>
                <li><span>节点3.1</span></li>
                <li><span>节点3.2</span></li>
            </ul>
        </li>
        <li>
            <span>节点4</span>
            <ul>
                <li  isexpand="false">
                    <span>节点4.1</span>
                    <ul>
                        <li>
                            <span>节点4.1.1</span>
                            <ul>
                                <li><span>节点4.1.1.2</span></li>
                                <li><span>节点4.1.1.2</span></li>
                            </ul>
                        </li>
                        <li><span>节点4.1.2</span></li>
                    </ul>
                </li>
                <li><span>节点4.2</span></li>
            </ul>
        </li>
    </ul>
    </div> 
     

        <div style="display:none">
     
    </div>
</body>
</html>