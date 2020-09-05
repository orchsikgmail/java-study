const express = require('express');
const { isLoggedIn, isNotLoggedIn } = require('./middlewares');

const router = express.Router();

let asyncFunction1 = (message)=> new Promise((resolve)=> {
  setTimeout(()=> {
      console.log('fn-1', message);
      resolve('fn-1');
  }, 1000);
});

let asyncFunction2 = (message)=> new Promise((resolve)=> {
  setTimeout(()=> {
      console.log('fn-2', message);
      resolve('fn-2');
  }, 500);
});
async function asyncMain () {
  let data = await asyncFunction1('hello');
  console.log(data);
  data = await asyncFunction2('world');
  console.log(data);
}

let loginRequired2 = (hasSignIn, req, res, next)=> new Promise((resolve) => {
  if (hasSignIn) {
    asyncMain();
    res.status(401).send({
      code: 401,
      message: '로그인을 한 후 이용해주세요.',
    });
    res.end();
  }
  next();
});

router.get('/profile',
  async function loginRequired(req, res, next) {
    await checkLogin(true);

    async function checkLogin(hasSignIn) {
      if (!hasSignIn) {
        console.log('aaaaa')
        res.status(401).send({
          code: 401,
          message: '로그인을 한 후 이용해주세요.',
        });
      } 
        next();
      
    }
  });

router.get('/join', (req, res) => {
  res.render('join', {
    title: '회원가입 - NodeBird',
    user: null,
    joinError: req.flash('joinError'),
  });
});


router.get('/', async (req, res, next) => {
  try {
    console.log("main");
    res.render('main', {
      title: "main",
      twits: [],
      user: null,
      loginError: req.flash('loginError'),
    });
  } catch (error) {
    console.log('예외발생')
  }
  });




module.exports = router;