var data;
var data2;
var data3;
var data4;

//commit3444
function buttonName_click(){
   
   if($("#inputBox").val()==""||$("#inputBox3").val()==""){
      alert("Input any data.");
   }else{
      //alert($("#inputBox").val());
      data = $("#inputBox").val();
      data2 = $("#inputBox2").val();
      data3 = $("#inputBox3").val();
      data4 = $("#inputBox4").val();
      ajaxCall();
   }   
}
function buttonName_click2(){
   $.ajax({
      url:"/HelloBeacon/order/test3",
      type:"POST",
      dataType:"json",
      success : function(res){
         $("#div1").empty();
         $.each(res, function(key,obj){
            $("#div1").append(
                  "<p>idx="+obj.idx+
                  " title="+obj.title+
                  " author="+obj.author+
                  " insert_date="+obj.insert_date+
                  " reg_date="+obj.reg_date+
                  " count="+obj.count+
                  "</p></br>"
                  );
         });
      },error : function(req,txt,err){
         $("#div1").append("<p>req : "+req+"</p></br>");
         $("#div1").append("<p>txt : "+txt+"</p></br>");
         $("#div1").append("<p>err : "+err+"</p></br>");
      }
   });
}

function buttonName_click3( ) {
	if($("#inputBox").val()==""||$("#inputBox3").val()==""){
	      alert("Input any data.");
	   }else{
	      //alert($("#inputBox").val());
	      data = $("#inputBox").val();
	      data2 = $("#inputBox2").val();
	      data3 = $("#inputBox3").val();
	      data4 = $("#inputBox4").val();
	      ajaxCall1();
	   }   
}

function buttonName_click4( ) {
	if($("#inputBox").val()==""){
	      alert("Input any data.");
	   }else{
	      //alert($("#inputBox").val());
	      data = $("#inputBox").val();
	      ajaxCall2();
	   }   
}
//fdsa
function ajaxCall(){
   var dataForm ={
      title:data,
      content:data2,
      author:data3,
      idx:data4
   };
   
   //alert("dataForm.title : "+dataForm.title+"\ndata.content : "+dataForm.content);
   $.ajax({
      url : "/HelloBeacon/order/test2",
      type: "POST",
      data: dataForm,
      success : function( res ){
      },
      error : function(req,txt,err){
         $("#div1").append("<p>req : "+req+"</p></br>");
         $("#div1").append("<p>txt : "+txt+"</p></br>");
         $("#div1").append("<p>err : "+err+"</p></br>");
         //alert("ajax Error!");
      }
   });
}

function ajaxCall1(){
	   var dataForm ={
	      title:data,
	      content:data2,
	      author:data3,
	      idx:data4
	   };
	   
	   //alert("dataForm.title : "+dataForm.title+"\ndata.content : "+dataForm.content);
	   $.ajax({
	      url : "/HelloBeacon/order/test4",
	      type: "POST",
	      data: dataForm,
	      success : function( res ){
	      },
	      error : function(req,txt,err){
	         $("#div1").append("<p>req : "+req+"</p></br>");
	         $("#div1").append("<p>txt : "+txt+"</p></br>");
	         $("#div1").append("<p>err : "+err+"</p></br>");
	         //alert("ajax Error!");
	      }
	   });
	}

function ajaxCall2(){
	   var dataForm ={
	      title:data
	   };
	   
	   //alert("dataForm.title : "+dataForm.title+"\ndata.content : "+dataForm.content);
	   $.ajax({
	      url : "/HelloBeacon/order/test5",
	      type: "POST",
	      data: dataForm,
	      success : function( res ){
	      },
	      error : function(req,txt,err){
	         $("#div1").append("<p>req : "+req+"</p></br>");
	         $("#div1").append("<p>txt : "+txt+"</p></br>");
	         $("#div1").append("<p>err : "+err+"</p></br>");
	         //alert("ajax Error!");
	      }
	   });
	}