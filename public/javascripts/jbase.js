function dir_add(id){
    var dir_name = prompt("Dir Name: ")
    if( dir_name === null ){
      alert(path)
    }else{
        $.get("/base/dirDML/add?id="+id+"&name="+dir_name,function(data,status){
            if(status === "success"){
              location.reload()
            }else{
              alert("error: " + status)
            }
        })

    }
}
