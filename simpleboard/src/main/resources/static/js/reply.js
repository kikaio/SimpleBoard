async function getList(postId, link, goLast) {
    const result = await axios.get(`/replies?postId=${postId}&${link}`);
    return result.data;
};