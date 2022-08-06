const btnRandomColorName = document.querySelector("#btn-random-color-name");

btnRandomColorName.addEventListener("click", async () => {
    try {
        let res = await axios.get("http://localhost:8080/random-color?type=4");
        console.log(res);

        document.body.style.backgroundColor = res.data;
    } catch (error) {
        console.log("Lỗi")
        console.log(error.response);
        alert(error.response.data.message);
    }
})