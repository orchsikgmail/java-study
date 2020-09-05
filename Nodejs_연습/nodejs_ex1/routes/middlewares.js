// 로그인이 됬을때 나오는거 내프로필 같은거
exports.isLoggedIn = (req, res, next) => {
  //if(req.isAuthenticated()){   로그인하면 true
    if (false) {
        next();
    } else {
        res.status(401).send({
            code: 401,
            message: '로그인을 한 후 이용해주세요.',
          });
    }
};

// 로그인이 안됬을때 나오는거 회원가입, 로그인 같은 거
exports.isNotLoggedIn = (req,res,next) => {
   // if(!req.isAuthenticated()){
    if (true) {
        res.status(401).send({
            code: 401,
            message: '로그인을 한 후 이용해주세요.',
        });
    } 
        next();
   

};