function getCount(startTime, endTime) {
  const betweenTime = Math.floor((endTime - startTime) / 1000 / 60)
  // 로직은 알아서 정하자 !
  const betweenTimeHour = Math.floor(betweenTime / 60)
  if (betweenTimeHour < 24) {
    return `${betweenTimeHour}시간`
  }

  const betweenTimeDay = Math.floor(betweenTime / 60 / 24)
  if (betweenTimeDay < 365) {
    return `${betweenTimeDay}일`
  }

  return `${Math.floor(betweenTimeDay / 365)}년`
}

export default getCount
