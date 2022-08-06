// Khởi tạo promise
let promise = new Promise((resolve, reject) => { });
console.log(promise);

let promiseSuccess = new Promise((resolve, reject) => {
    resolve("Thực hiện lời hứa thành công");
});
console.log(promiseSuccess);

let promiseError = new Promise((resolve, reject) => {
    reject("Thực hiện lời hứa thất bại");
});
console.log(promiseError);

// HỨA : Cuối năm nếu có trên 30 triệu, sẽ mua iphone 13 pro max

let money = 20;

const buyIphone = () => {
    return new Promise((resolve, reject) => {
        if (money >= 30) {
            resolve("Đủ tiền mua iPhone");
        } else {
            reject("Không đủ tiền, cày tiếp để năm sau mua");
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

console.log(buyIphone());

// buyIphone()
//     .then(res => console.log(res))
//     .catch(error => console.log(error));

// buyAirpod()
//     .then(res => console.log(res))
//     .catch(error => console.log(error));

buyIphone()
    .then(res => {
        console.log(res);
        return buyAirpod();
    })
    .then(res1 => {
        console.log(res1);
    })
    .catch(error => console.log(error))
    .finally(() => {
        console.log("Về nhà")
    })