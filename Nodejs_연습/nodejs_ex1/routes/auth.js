const express = require('express');
const { isLoggedIn, isNotLoggedIn }  = require('./middlewares');


const router = express.Router();


router.post('/join', isNotLoggedIn, async (req, res, next)=>{
    const { email,nick,password } = req.body;
    try{
        console.log('가입성공');
    } catch (error) {
        console.error(error);
        return next(error); 
    }
});




module.exports = router;