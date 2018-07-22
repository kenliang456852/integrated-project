    //js获取项目根路径，如： http://localhost:8083/uimcardprj
    // function getRootPath(){
        //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var cur_www_path = window.document.location.href;
    console.log("cur_www_path -- " + cur_www_path);
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var path_name = window.document.location.pathname;
    console.log("path_name -- " + path_name);
    var pos = cur_www_path.indexOf(path_name);
    console.log("pos -- " + pos);
    //获取主机地址，如： http://localhost:8083
    var local_host_Path = cur_www_path.substring(0, pos);
    console.log("local_host_Path -- " + local_host_Path);
    //获取带"/"的项目名，如：/uimcardprj
    var projectName = path_name.substring(0, path_name.substr(1).indexOf('/')+1);
    console.log("projectName -- " + projectName);
    var all_path = local_host_Path + projectName;
    console.log("all_path -- " + all_path);
    var $ctx = local_host_Path + "/";
    console.log("$ctx -- " + $ctx);

    // }