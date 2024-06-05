async function uploadToServer(formObj) {
    console.log("upload to server");
    console.log(formObj);

    const ret = await axios({
        method:'post'
        , url:'/upload'
        , data : formObj
        , headers :{
            'Content-Type':'multipart/form-data'
            ,
        }
        ,
    });

    return ret.data;
}

async function removeFileToServer(uuid, fileName) {
    const ret = await axios.delete(`/remove/${uuid}_${fileName}`);
    return ret.data;
}