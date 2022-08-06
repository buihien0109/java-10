let money = 40;

const buyIphone = () => {
    return new Promise((resolve, reject) => {
        if (money >= 30) {
            resolve("Đủ tiền mua iPhone");
        } else {
            reject("Không đủ tiền mua iPhone, cày tiếp để năm sau mua");
        }
    })
}

const buyAirpod = () => {
    return new Promise((resolve, reject) => {
        if (money - 30 - 4 >= 0) {
            resolve("Mua thêm airpod")
        } else {
            reject("Không đủ tiền mua airpod")
        }
    })
}

// async function buy() {}

// const buy = async function() {}

const buy = async () => {
    try {
        let res = await buyIphone();
        console.log(res);

        let res1 = await buyAirpod();
        console.log(res1);
    } catch (error) {
        console.log(error);
    }

    return "Về nhà";
}

// console.log(buy());
buy().then(res => console.log(res));