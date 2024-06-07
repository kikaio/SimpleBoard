async function getList(postId, link, goLast) {
    const result = await axios.get(`/replies?postId=${postId}&${link}`);
    if(goLast) {
        const total = result.data.total;
        const page = parseInt(result.data.page);
        const pageSize = parseInt(result.data.size);
        const lastPage = parseInt(result.data.end);

        let newLink=`page=${lastPage}&pageSize=${pageSize}`;
        return getList(postId, newLink, false);
    }
    return result.data;
};


async function addReply(replyObj) {
    const ret = await axios.post(`/replies`, replyObj);
    return ret.data;
}


async function getReply(id) {
    const ret = await axios.get(`/replies/${id}`);
    return ret.data;
}

async function modifyReply(replyObj) {
    const ret = await axios.put(`/replies/${replyObj.id}`, replyObj);
    return ret.data;
}

async function removeReply(id, replyObj) {
    const ret = await axios.delete(`/replies/${id}`, {
        data : replyObj
    });
    return ret.data;
}