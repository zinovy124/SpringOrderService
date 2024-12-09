export const load = async ({ params }) => {
    return {
        menuId: params.menuId,
        userId: await fetch('http://localhost:8080/api/user'),
    }
}