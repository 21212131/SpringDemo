<!DOCTYPE html>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<html>
<head>
    <meta charset="utf-8">
    <title>Road to Games</title>
</head>
<body>

<h1>Road to Games</h1>
<p id="demo">
    JavaScript can change page's element
</p>
<button type="button" onclick="myFunction()">try this!</button><br>
<div id="gameList"></div>
<script>
    document.write("<p>Hi! this is ${name} from ${city}!</p>");
    getAllGame();
    function getAllGame(){
        var url= "http://localhost:8080/getAllGames";
        console.log(url);
        getJSON(url).then( function(data){
                var info="<table style={{border: '1px solid black'}}>" +
                          "<thead style=\"color:red\"><tr>\n"+
                          "   <th>Game Name</th>\n" +
                          "   <th>Game Type</th>\n" +
                          "</tr></thead>\n"+
                          "<tbody>\n";
                console.log(data);
                for(var i=0; i<data.length; i++){
                    var temp =
                        "  <tr key="+data[i].game_name+">\n"+
                        "    <td style={{width: '200px', textAlign: 'center'}}>"+data[i].game_name+"</td>\n"+
                        "    <td style={{width: '200px', textAlign: 'center'}}>"+data[i].game_type+"</td>\n"+
                        "  </tr>\n";
                    info = info + temp;
                     // info = info + data[i].game_name + "<br>";
                }
                info = info + "</tbody>"+"</table>";
                document.getElementById("gameList").innerHTML = info;
            },
            function(status) {
                alert('Something went wrong.');
            });
    }

    function myFunction()
    {
        x=document.getElementById("demo");  // 找到元素
        x.innerHTML="Hello JavaScript!";    // 改变内容
    }

    function getJSON(url){
        return new Promise( function(resolve, reject){
            var xhr = new XMLHttpRequest();
            xhr.open('get',url,true);
            xhr.responseType ='json';
            xhr.onload =  function(){
                var status = xhr.status;
                if (status == 200) {
                    resolve(xhr.response);
                } else {
                    reject(status);
                }
            };
            xhr.send();
        });
    };
    function startHttpQuery(gameID,gameName,gameType){
        var url= "http://localhost:8080/addGame";
        if(null != gameName){
            //get方法的查询参数设置
            url=url + "?gameID="+gameID+"&gameName=" + gameName+"&gameType="+gameType;
        }
        getJSON(url).then( function(data){
            },
            function(status) {
                alert('Something went wrong.');
            });
        getAllGame();
    }





    function startQuery(){
        var gameID=document.getElementById("gameID").value;
        var gameName=document.getElementById("gameName").value;
        var gameType=document.getElementById("gameType").value;
        startHttpQuery(gameID,gameName,gameType);
    }

    function test(){
        var m = {
            "name": document.getElementById("name").value,
            "sex":document.getElementById("sex").value
        };
        $.ajax({
            type:"POST",
            async : false,
            url:"/test",
            dataType:"json",
            contentType:"application/json; charset=utf-8",
            data:JSON.stringify(m),
            success:function(data){
                console.log(data);
                alert("Success!");
            },
            error:function(data){
                alert("Fail! ")
            }
        })
    }

    function test2(){
        var url= "http://localhost:8080/test2/"+document.getElementById("age").value;
        getJSON(url).then( function(data){
            },
            function(status) {
                alert('Something went wrong.');
            });
    }


    document.write("<p>${games.get(1).getGame_name()}</p>");
    for(var i=0;i<3;){
        document.write("<p>${games.get(i).getGame_name()}</p>");
        i=i+1;
    }
</script>
game id:<input type="text" id="gameID" /><br>
game name:<input type="text" id="gameName" /><br>
game type:<input type="text" id="gameType" /><br>
<button onclick="startQuery()">add a game</button><br>
name:<input type="text" id="name" /><br>
sex:<input type="text" id="sex"/><br>
<button onclick="test()">test RequestBody</button><br>
age:<input type="text" id="age"/><br>
<button onclick="test2()">test PathVariable</button>


</body>
</html>